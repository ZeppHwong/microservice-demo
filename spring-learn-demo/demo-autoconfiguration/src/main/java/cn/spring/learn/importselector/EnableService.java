package cn.spring.learn.importselector;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(ServiceImportBeanDefinitionRegistrar.class)
//@Import(EnableServiceImportSelector.class)
public @interface EnableService {
    //    Class<?> name();
    String name();
    Class<?> clz();
}
