package cn.demo.autoconfiguration;


import cn.demo.api.HelloImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Service1AutoConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "service", name = "name", havingValue = "service1")
    public HelloImpl helloImpl() {
        return new HelloImpl();
    }
}
