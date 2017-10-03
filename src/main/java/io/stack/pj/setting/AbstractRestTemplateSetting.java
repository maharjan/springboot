package io.stack.pj.setting;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Default value can  be overridden on property file.
 *
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public abstract class AbstractRestTemplateSetting {

    /**
     * Time in milliseconds a request will wait till connection to server is established
     */
    private int connectTimeout = 10000;

    /**
     * Time in milliseconds a request will wait till data is received after connection is established.
     * alias SocketTimeout
     */
    private int socketTimeout = 10000;

    /**
     * Limits the number of connections that will be made to a single IP:Port pair
     */
    private int maxPerRoute = 25;

    /**
     * limits the number of total connections that will ever be opened
     */
    private int maxTotalConnection = 100;
}
