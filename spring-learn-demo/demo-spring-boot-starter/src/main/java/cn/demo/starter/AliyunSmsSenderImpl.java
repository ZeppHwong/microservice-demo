package cn.demo.starter;

public class AliyunSmsSenderImpl implements ISmsSender{
    private SmsProperties.SmsMessage smsMessage;


    public AliyunSmsSenderImpl(SmsProperties.SmsMessage smsProperties) {
        this.smsMessage = smsProperties;
    }
    @Override
    public void send(String message) {
        System.out.println("AliyunSmsSenderImpl.send"+smsMessage);
    }
}
