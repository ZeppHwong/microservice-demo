package cn.demo.configuration;

import cn.demo.api.Service1On;
import cn.demo.api.Service2Condition;
import cn.demo.api.Service2On;
import cn.demo.api.ServiceCondition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
public class ServiceConfig {
    @Bean
    @Conditional(Service2Condition.class)
    public Service2On service2On(ServiceProperties properties) {
        return new Service2On(properties.getName());
    }

    @Bean
    @Conditional(ServiceCondition.class)
    public Service1On service1On(ServiceProperties properties) {
        return new Service1On(properties.getName());
    }
}
