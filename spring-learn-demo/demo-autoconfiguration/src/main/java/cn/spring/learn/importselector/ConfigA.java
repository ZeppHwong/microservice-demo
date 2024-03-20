package cn.spring.learn.importselector;

import org.springframework.context.annotation.Configuration;

//@Import(ConfigB.class)
//@Import(ServiceImportSelector.class)
@EnableService(name = "TestServiceC")
@Configuration
public class ConfigA {
//    @Bean
//    @ConditionalOnMissingBean
    public IService getServiceA() {
        return new ServiceA();
    }
}
