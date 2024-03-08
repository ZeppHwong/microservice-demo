package cn.demo.autoconfiguration;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
//@ConditionalOnProperty(prefix = "service", name = "name", havingValue = "service1")
public class Service1AutoConfiguration {

}
