package cn.demo.webflux.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class GreetingController {
    @GetMapping("/hello")
    public Mono<String> handle() {
        return Mono.just("Hello WebFlux");
    }

    @GetMapping("/hello2")
    public Flux<String> handle2() {
        List<String> list = Arrays.asList("Hello WebFlux", "Hello WebFlux2");
        return Flux.fromIterable(list);
    }

    @GetMapping("monoOnSubscription")
    public Mono<Object> mono() {
        return Mono.create(monoSink -> {
                    log.info("创建 Mono");
                    monoSink.success("hello webflux");
                })
                .doOnSubscribe(subscription -> {
                    log.info("{}", subscription);
                }).doOnNext(o -> {
                    log.info("{}", o);
                });
    }
}
