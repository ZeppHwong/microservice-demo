package cn.spring.learn.autoconfig;


import cn.demo.starter.AliyunSmsSenderImpl;
import cn.demo.starter.TencentSmsSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SmsSendCommandLineRunner implements CommandLineRunner {
    @Autowired
    AliyunSmsSenderImpl aliyunSmsSender;

    @Autowired
    TencentSmsSenderImpl tencentSmsSender;
    @Override
    public void run(String... args) throws Exception {
        aliyunSmsSender.send("aliyun send sms");
        tencentSmsSender.send("tencent send sms");
    }
}
