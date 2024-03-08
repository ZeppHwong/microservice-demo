package cn.demo.grpcserver.service;

import cn.demo.grpc.api.HelloReply;
import cn.demo.grpc.api.HelloRequest;
import cn.demo.grpc.api.MyServiceGrpc;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase{
    @NacosValue(value = "${MyService:MyServiceGrpc}", autoRefreshed = true)
    private String myService;
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + myService)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
