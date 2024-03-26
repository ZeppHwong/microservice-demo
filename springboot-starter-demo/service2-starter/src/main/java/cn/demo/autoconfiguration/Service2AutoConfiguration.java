package cn.demo.autoconfiguration;


import cn.demo.api.HelloImpl2;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class Service2AutoConfiguration {
//    @Bean
//    @ConditionalOnProperty(prefix = "service", name = "name", havingValue = "service2")
//    @ConditionalOnMissingBean
    public HelloImpl2 helloImpl() {
        return new HelloImpl2();
    }
}
