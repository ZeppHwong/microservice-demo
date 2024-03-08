package cn.demo.grpcserver;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//springboot
@NacosPropertySource(dataId = "GrpcServer", autoRefreshed = true)
//@EnableDiscoveryClient  // 开启nacos服务发现
public class GrpcServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrpcServerApplication.class, args);
    }
}
