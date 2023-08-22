package cn.demo.order.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {
    /**
     * open feign log
     */
    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }
}
