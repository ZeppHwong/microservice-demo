package cn.demo.learn.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class AkkaActorExample {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("HelloAkka");

        // 创建 Actor 实例
        ActorRef helloActor = system.actorOf(HelloWorldActor.props(), "helloActor");

        // 向 Actor 发送消息
        helloActor.tell("Hi there!", ActorRef.noSender());

        // 为了让程序不立即退出，可以添加一些延时或者使用其他方式维持 ActorSystem 的运行
        // 这里仅作为示例，实际应用中应有适当的退出逻辑
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            system.terminate();
        }
    }
}
