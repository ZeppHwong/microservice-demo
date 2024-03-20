package cn.spring.learn.lookup;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {
    private static final Logger logger= LoggerFactory.getLogger(PrototypeBean.class);

    public void say() {
        logger.info("say something...");
    }
}
