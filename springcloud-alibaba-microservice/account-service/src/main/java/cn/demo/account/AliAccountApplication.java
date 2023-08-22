package cn.demo.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient  // 开启nacos服务发现
@EnableFeignClients // 开启 openfeign 服务发现
public class AliAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliAccountApplication.class, args);
    }
}
