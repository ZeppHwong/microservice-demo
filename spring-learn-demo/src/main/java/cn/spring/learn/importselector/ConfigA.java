package cn.spring.learn.importselector;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
