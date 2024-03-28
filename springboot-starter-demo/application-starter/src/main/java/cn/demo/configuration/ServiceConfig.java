package cn.demo.configuration;

import cn.demo.api.Service1On;
import cn.demo.api.Service2On;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    @Conditional(Service2Condition.class)
    public Service2On service2On() {
        return new Service2On();
    }


    @Bean
    @Conditional(ServiceCondition.class)
    public Service1On service1On() {
        return new Service1On();
    }
}
