package me.zbl.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class SpringAsyncConfig {
  //    @Bean
  //    public AsyncTaskExecutor taskExecutor() {
  //        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
  //        executor.setMaxPoolSize(10);
  //        return executor;
  //    }
}