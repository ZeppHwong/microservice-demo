package cn.demo.starter;

public class TencentSmsSenderImpl implements ISmsSender {
    private SmsProperties.SmsMessage smsMessage;


    public TencentSmsSenderImpl(SmsProperties.SmsMessage smsProperties) {
        this.smsMessage = smsProperties;
    }

    @Override
    public void send(String message) {
        System.out.println("TecentSmsSenderImpl.send" + smsMessage);
    }
}
