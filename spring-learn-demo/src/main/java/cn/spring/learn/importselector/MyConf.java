package cn.spring.learn.importselector;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MyAutoConfiguredMyBatisRegistrar.class)
//@ComponentScan("cn.spring.learn.importselector")
public @interface MyConf {
}
