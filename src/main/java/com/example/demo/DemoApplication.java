package com.example.demo;

import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        log.info("\n\n\t---------------------------------------------\n\t"
                + ":: Saturno backend inicializado na porta: " + environment.getProperty("server.port") + " ::"
                + "\n\t---------------------------------------------\n\t");
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(100000);
        executor.setThreadNamePrefix("Demo-");
        executor.initialize();
        return executor;
    }
}
