package cn.spring.learn.lookup;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public abstract class SingletonBean {
    private static final Logger logger = LoggerFactory.getLogger(SingletonBean.class);
    @Autowired
    private ApplicationContext context;
    @Autowired
    private PrototypeBean bean;

    public void print() {
        logger.info("Autowired Bean SingletonBean's HashCode : {}", bean.hashCode());
        bean.say();
    }

    public void printFromContext() {
        PrototypeBean prototypeBean = getFromApplicationContext();
        logger.info("ApplicationContext Bean SingletonBean's HashCode : {}", prototypeBean.hashCode());
        prototypeBean.say();
    }

    public void printFromLookup() {
        PrototypeBean prototypeBean = methodInject();
        logger.info("Lookup Bean SingletonBean's HashCode : {}", prototypeBean.hashCode());
        prototypeBean.say();
    }

    // 也可以写成 @Lookup("prototypeBean") 来指定需要注入的bean
    @Lookup
    protected abstract PrototypeBean methodInject();
    /**
     * 每次都从ApplicatonContext中获取新的bean引用
     *
     * @return PrototypeBean instance
     */
    PrototypeBean getFromApplicationContext() {
        return this.context.getBean("prototypeBean", PrototypeBean.class);
    }
}
