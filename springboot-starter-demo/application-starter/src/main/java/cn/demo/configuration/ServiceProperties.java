package cn.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ServiceProperties.SERVER2_PREFIX)
public class ServiceProperties {
    public static final String SERVER2_PREFIX = "service";
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
