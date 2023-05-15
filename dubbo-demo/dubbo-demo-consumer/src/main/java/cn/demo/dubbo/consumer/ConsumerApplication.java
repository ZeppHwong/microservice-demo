package cn.demo.dubbo.consumer;


//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
//import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
//@NacosPropertySource(dataId = "dubbo-demo-consumer", autoRefreshed = true)
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
