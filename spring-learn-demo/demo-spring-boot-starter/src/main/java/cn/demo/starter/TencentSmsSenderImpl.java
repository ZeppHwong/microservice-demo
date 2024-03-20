package cn.demo.starter;

import org.springframework.beans.factory.annotation.Autowired;

public class TencentSmsSenderImpl implements ISmsSender {

    private SmsProperties.SmsMessage smsMessage;

    @Autowired
    SmsDomain smsDomain;


    public TencentSmsSenderImpl(SmsProperties.SmsMessage smsProperties) {
        this.smsMessage = smsProperties;
    }

    @Override
    public void send(String message) {
        System.out.println("TecentSmsSenderImpl.send" + smsMessage);
        smsDomain.sendOver();
    }
}
