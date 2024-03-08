package cn.demo.grpcclient.controller;

import cn.demo.grpcclient.service.GrpcClientService;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HelloController {
    @Autowired
    GrpcClientService grpcClientService;
    @NacosInjected
    private NamingService namingService;

    @GetMapping("/hello")
    public String hello() {
        return grpcClientService.hello();
    }

    @GetMapping("/get")
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }
}
