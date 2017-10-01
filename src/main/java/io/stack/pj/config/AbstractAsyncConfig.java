package io.stack.pj.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * The executor adjust the pool size based on core and max value. If during task submission, the threads running are lesser than core size
 * then new threads are created. If the threads are equal or greater then task are queued. If queue limit is reached, then new threads are
 * created till max size. After reaching max size, all new submitted task are rejected.
 *
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@EnableAsync
public abstract class AbstractAsyncConfig implements AsyncConfigurer {

    protected static final int QUEUE_SIZE = 1000;

    protected static final String ASYNC_PREFIX = "async-";

    protected Executor getExecutor(final String name, final int minSize) {
        log.info("Creating async executor with name {} of size {}", name, minSize);
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(minSize);
        executor.setMaxPoolSize(minSize * minSize);
        executor.setQueueCapacity(QUEUE_SIZE);
        executor.setThreadNamePrefix(ASYNC_PREFIX + name + "-");
        executor.initialize();
        return executor;
    }

    protected Executor getScheduleExecutor(final String name, final int minSize) {
        log.info("Creating async executor with name {} of size {}", name, minSize);
        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(minSize);
        executor.setRemoveOnCancelPolicy(true);
        return executor;
    }

    public AsyncUncaughtExceptionHandler getExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}