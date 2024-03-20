package cn.spring.learn.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Objects;

public class EnableServiceImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //这里的importingClassMetadata针对的是使用@EnableService的非注解类
        //因为`AnnotationMetadata`是`Import`注解所在的类属性，如果所在类是注解类，则延伸至应用这个注解类的非注解类为止
        Map<String , Object> map = importingClassMetadata.getAnnotationAttributes(EnableService.class.getName(), true);
        String name = (String) map.get("name");
        if (Objects.equals(name, "B")) {
            return new String[]{"cn.spring.learn.importselector.ConfigB"};
        }
        return new String[0];
    }
}
