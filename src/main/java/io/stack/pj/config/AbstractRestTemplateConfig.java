package io.stack.pj.config;

import io.stack.pj.Setting.AbstractRestTemplateSetting;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Slf4j
public abstract class AbstractRestTemplateConfig {

    //5 secs
    private static final int VALIDATE = 5000;
    private static final int MIN = 0;
    // 5 mins
    private static final int MAX = 300000;
    private static final int POOL = 3000;

    private static final int RETRY = 2;

    private HttpComponentsClientHttpRequestFactory getClientRequestFactory(AbstractRestTemplateSetting setting) {
        Assert.isTrue(setting.getConnectTimeout() > MIN && setting.getConnectTimeout() <= MAX,
                "The connect timeout must be greater than " + MIN + " and lesser than or equal to " + MAX);
        Assert.isTrue(setting.getSocketTimeout() > MIN && setting.getSocketTimeout() <= MAX,
                "The read/socket timeout must be greater than " + MIN + " and lesser than or equal to " + MAX);
        Assert.isTrue(setting.getMaxPerRoute() > MIN && setting.getMaxPerRoute() <= POOL,
                "The max per route must be greater than " + MIN + " and lesser than or equal to " + POOL);
        Assert.isTrue(setting.getMaxTotalConnection() > MIN && setting.getMaxTotalConnection() <= POOL,
                "The max total connection must be greater than " + MIN + " and lesser than or equal to " + POOL);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(setting.getConnectTimeout())
                .setSocketTimeout(setting.getSocketTimeout())
                .setConnectionRequestTimeout(setting.getConnectTimeout())
                .build();

        final PoolingHttpClientConnectionManager poolingHttpConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpConnectionManager.setMaxTotal(setting.getMaxTotalConnection());
        poolingHttpConnectionManager.setDefaultMaxPerRoute(setting.getMaxPerRoute());
        poolingHttpConnectionManager.setValidateAfterInactivity(VALIDATE);

        final CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(poolingHttpConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setMaxConnTotal(setting.getMaxTotalConnection())
                .setMaxConnPerRoute(setting.getMaxPerRoute())
                .setKeepAliveStrategy(keepAliveStrategy())
                .setRetryHandler(retryHandler())
                .build();
//        new IdleConnectionMonitorThread(poolingHttpConnectionManager).run();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    private ConnectionKeepAliveStrategy keepAliveStrategy() {
        return new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // Honor 'keep-alive' header
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException ignore) {
                            log.error("Parse error: {}", ignore.getMessage());
                        }
                    }
                }
//                HttpHost target = (HttpHost) context.getAttribute(
//                        HttpClientContext.HTTP_TARGET_HOST);
                // otherwise keep alive for 10 seconds
                return 10000;
            }
        };
    }

    private HttpRequestRetryHandler retryHandler() {
        return new HttpRequestRetryHandler() {

            @Override
            public boolean retryRequest(IOException exception, int executionCount,
                                        HttpContext context) {
                if (executionCount > RETRY) {
                    log.warn("Maximum tries reached for client http pool ");
                    return false;
                }
                if (exception instanceof org.apache.http.NoHttpResponseException) {
                    log.warn("No response from server on " + executionCount + " call");
                    return true;
                }
                return false;
            }
        };
    }

    public static class IdleConnectionMonitorThread extends Thread {

        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        // Close expired connections
                        connMgr.closeExpiredConnections();
                        // Optionally, close connections that have been idle longer than 30 sec
                        connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
                // terminate
                log.error("Idle connection monitoring error: {}", ex);
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }

    protected RestTemplate initRestTemplate(AbstractRestTemplateSetting setting) {
        log.info("Rest Template config: {}", setting.toString());
        RestTemplate restTemplate = new RestTemplate(getClientRequestFactory(setting));
        restTemplate.setErrorHandler(new RestErrorHandlerImpl());
        return restTemplate;
    }
}
