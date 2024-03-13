package cn.spring.learn.lookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//@Component
public class BeanCommandLineRunner implements CommandLineRunner {
    @Autowired
    SingletonBean bean;
    private static final Logger logger = LoggerFactory.getLogger(BeanCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("测试单例bean和原型bean的调用");
        int i = 0;
        while (i < 3) {
            i++;
            bean.print();
            bean.printFromContext();
            bean.printFromLookup();
        }
    }
}
