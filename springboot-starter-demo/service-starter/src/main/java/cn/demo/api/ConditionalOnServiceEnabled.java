package cn.demo.api;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ConditionalOnProperty(prefix = "service", name = "name", havingValue = "service1")
public @interface ConditionalOnServiceEnabled {
}
