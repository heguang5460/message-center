package heguang5460.github.io.message.web.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author he guang
 * @date 2021/9/3 13:10
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(
                4,
                8,
                120,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(256)
        );
    }

}
