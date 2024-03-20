package cn.spring.learn.importselector;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigB {
    @Bean
    @ConditionalOnMissingBean
    public IService getServiceB() {
        return new ServiceB();
    }
}
