package io.stack.pj.config;

import io.stack.pj.shared.PropertyNames;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.Executor;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Configuration
@PropertySource(value= PropertyNames.APP_PROP_FILE, ignoreResourceNotFound = true)
public class AsyncExecutorConfig extends AbstractAsyncConfig {

    @Value(PropertyNames.ASYNC_POOL)
    private static int MIN_SIZE = 25;

    private static final String NAME = "spring-boot";

    @Override
    public Executor getAsyncExecutor() {
        return getExecutor(NAME, MIN_SIZE);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return getExceptionHandler();
    }
}
