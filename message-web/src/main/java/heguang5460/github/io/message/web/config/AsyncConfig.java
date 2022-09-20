package heguang5460.github.io.message.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


/**
 * 配置Spring Async配置
 *
 * @author heguang
 */
@Slf4j
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    /**
     * 自定义异步线程池，若不重写会使用默认的线程池
     *
     * @author heugang
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //  核心线程数
        taskExecutor.setCorePoolSize(4);
        //  最大线程数
        taskExecutor.setMaxPoolSize(8);
        //  队列大小
        taskExecutor.setQueueCapacity(256);
        //  线程名的前缀
        taskExecutor.setThreadNamePrefix("async-thread-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * 异常处理
     *
     * @author heugang
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        //  异步执行异常处理，暂时不处理
        return (throwable, method, objects) -> log.error("async-thread error, cause by: " + throwable.getMessage());
    }

}
