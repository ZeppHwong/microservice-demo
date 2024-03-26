package cn.spring.learn.importselector;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBConfig {
    @Bean
    @ConditionalOnMissingBean
    public IService serviceB() {
        return new ServiceB();
    }
}
