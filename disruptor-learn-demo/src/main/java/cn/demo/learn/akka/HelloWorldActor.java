package cn.demo.learn.akka;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class HelloWorldActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, message -> {
                    System.out.println("Received message: " + message);
                    sender().tell("Hello from " + getSelf().path().name(), getSelf());
                })
                .build();
    }
    public static Props props() {
        return Props.create(HelloWorldActor.class);
    }
}
