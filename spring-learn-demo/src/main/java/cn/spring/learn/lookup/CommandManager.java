package cn.spring.learn.lookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Lookup;

public abstract  class CommandManager {
    private static final Logger logger = LoggerFactory.getLogger(CommandManager.class);

    public void print() {
        PrototypeBean bean = methodInject();
        logger.info("Bean SingletonBean's HashCode : {}",bean.hashCode());
        bean.say();
    }
    // 也可以写成 @Lookup("prototypeBean") 来指定需要注入的bean
    @Lookup
    protected abstract PrototypeBean methodInject();
}
