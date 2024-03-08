package cn.demo.grpcclient.service;

import cn.demo.grpc.api.HelloReply;
import cn.demo.grpc.api.HelloRequest;
import cn.demo.grpc.api.MyServiceGrpc;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;


@Service
public class GrpcClientService {
    @GrpcClient("myGrpcServer")
    MyServiceGrpc.MyServiceBlockingStub myServiceBlockingStub;

    public String hello() {
        HelloRequest request = HelloRequest.newBuilder().setName("hello").build();
        HelloReply helloReply = myServiceBlockingStub.sayHello(request);
        return helloReply.getMessage();
    }
}
