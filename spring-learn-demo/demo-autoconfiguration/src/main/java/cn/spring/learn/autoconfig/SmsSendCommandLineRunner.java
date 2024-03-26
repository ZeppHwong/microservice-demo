package cn.spring.learn.autoconfig;


import cn.demo.starter.ISmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class SmsSendCommandLineRunner implements CommandLineRunner {
//    @Autowired
//    AliyunSmsSenderImpl aliyunSmsSender;
//
//    @Autowired
//    TencentSmsSenderImpl tencentSmsSender;

    @Autowired
    ISmsSender sender;

    @Override
    public void run(String... args) throws Exception {
//        aliyunSmsSender.send(" send sms");
//        tencentSmsSender.send(" send sms");
        sender.send(" send sms");
    }
}
