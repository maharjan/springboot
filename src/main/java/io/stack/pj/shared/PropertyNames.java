package io.stack.pj.shared;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class PropertyNames {

    public static final String APP_PROP_FILE="file:${catalina.home}/conf/springboot_prop.yml";
    public static final String ASYNC_POOL = "${pool.async-size}";
    public static final String SESSION_TIMEOUT = "${session.timeout}";

    private PropertyNames() {
    }

}
