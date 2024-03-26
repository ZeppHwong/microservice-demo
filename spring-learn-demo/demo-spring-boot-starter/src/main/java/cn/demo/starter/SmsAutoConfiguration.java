package cn.demo.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(value = SmsProperties.class)
@Configuration
public class SmsAutoConfiguration {



    /**
     * 阿里云发送短信的实现类
     *
     * @param smsProperties
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "sms", name = "type",havingValue = "aliyun")
    public AliyunSmsSenderImpl aliYunSmsSender(SmsProperties smsProperties) {
        return new AliyunSmsSenderImpl
                (smsProperties.getAliyun());
    }

    /**
     * 腾讯云发送短信的实现类
     *
     * @param smsProperties
     * @return
     */
    @Bean
//    @ConditionalOnProperty(prefix = "sms", name = "type",havingValue = "tencent")
    @ConditionalOnMissingBean(ISmsSender.class)
    public TencentSmsSenderImpl tencentSmsSender(SmsProperties smsProperties) {
        return new TencentSmsSenderImpl(smsProperties.getTencent());
    }

}
