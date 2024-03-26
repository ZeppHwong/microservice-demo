package cn.spring.learn.importselector;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * 在 @Bean 注解之后加载，所以要修改ConfigA去掉其中被@ConditionalOnMissingBean注解的Bean，
 * 否则一定会生成ConfigA的ServiceInterface
 */
public class ServiceImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
   /*     Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableService.class.getName(), true);
        String name = (String) map.get("name");
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(ServiceC.class)
                //增加构造参数
                .addConstructorArgValue(name);
        //注册Bean
        registry.registerBeanDefinition("serviceC", beanDefinitionBuilder.getBeanDefinition());*/

        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableService.class.getName(), false);
        String name = (String) map.get("name");
        Class<?> clz = (Class<?>) map.get("clz");
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(clz)
        //增加构造参数
                .addConstructorArgValue(name);
        //注册Bean
        registry.registerBeanDefinition(clz.getSimpleName(), beanDefinitionBuilder.getBeanDefinition());
    }
}
