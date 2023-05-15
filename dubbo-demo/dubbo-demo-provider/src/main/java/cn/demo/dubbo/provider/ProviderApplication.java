package cn.demo.dubbo.provider;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@NacosPropertySource(dataId = "dubbo-demo-provider", autoRefreshed = true)
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}

