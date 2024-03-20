package cn.demo.starter;

import org.springframework.beans.factory.annotation.Autowired;


public class AliyunSmsSenderImpl implements ISmsSender{
    private SmsProperties.SmsMessage smsMessage;
    @Autowired
    SmsDomain smsDomain;

    public AliyunSmsSenderImpl(SmsProperties.SmsMessage smsProperties) {
        this.smsMessage = smsProperties;
    }
    @Override
    public void send(String message) {
        System.out.println("AliyunSmsSenderImpl.send"+smsMessage);
        smsDomain.sendOver();
    }
}
