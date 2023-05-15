package cn.demo.dubbo.provider;

import cn.demo.dubbo.api.DemoService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0")
public class DemoServiceImpl implements DemoService {
    @NacosValue(value = "${provider-name:demo-provider}",autoRefreshed = true)
    private String providerName;
    @Override
    public String sayHello(String name) {
        return "Hello " + name + ", I'm " + providerName;
    }
}
