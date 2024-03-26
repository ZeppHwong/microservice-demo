package cn.spring.learn.importselector;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestImportSelector {

    @Test
    public void testImportSelect() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ServiceConfig.class);
        IService bean = ctx.getBean(IService.class);
        bean.test();
    }
    @Test
    public void testAutoScanImportSelect() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConf.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            System.out.println(beanDefinitionNames[i]);
        }
    }
}
