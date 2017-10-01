package io.stack.pj.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
public class RestErrorHandlerImpl implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED) {
            return false;
        }
        return true;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error("Response error: {} , {}", response.getStatusCode(), response.getStatusText());
    }
}