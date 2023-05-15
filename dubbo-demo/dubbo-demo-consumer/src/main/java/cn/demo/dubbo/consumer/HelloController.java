package cn.demo.dubbo.consumer;

import cn.demo.dubbo.api.DemoService;
//import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @DubboReference(version = "1.0.0")
    private DemoService demoService;

    @GetMapping("/hello")
    public String sayHello(@RequestParam("name") String name){
       return demoService.sayHello(name);
    }

}
