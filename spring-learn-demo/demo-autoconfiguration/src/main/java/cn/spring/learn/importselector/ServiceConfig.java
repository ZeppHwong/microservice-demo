package cn.spring.learn.importselector;

import org.springframework.context.annotation.Configuration;

//@Import(ConfigB.class)
//@Import(ServiceImportSelector.class)
@EnableService(name = "name",clz = ServiceC.class)
@Configuration
public class ServiceConfig {
//    @Bean
//    @ConditionalOnMissingBean
    public IService serviceA() {
        return new ServiceA();
    }


}
