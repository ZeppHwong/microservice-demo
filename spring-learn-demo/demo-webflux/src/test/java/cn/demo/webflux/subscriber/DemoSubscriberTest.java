package cn.demo.webflux.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;


@Slf4j
public class DemoSubscriberTest {

    @Test
    public void testDoOnMethods() {
        Flux.range(1, 3)
                .log()
                .doOnSubscribe(e -> log.info("doOnSubscribe:{}", e))
                .doOnEach(e -> log.info("doOnEach:{}", e))
                .doOnError(e -> log.info("doOnError:{}", e))
                .doOnNext(e -> log.info("doOnNext:{}", e))
                .doOnRequest(e -> log.info("doOnRequest:{}", e))
                .doOnTerminate(new Runnable() {
                    @Override
                    public void run() {
                        log.info("doOnTerminate");
                    }
                })
                .doOnCancel(new Runnable() {
                    @Override
                    public void run() {
                        log.info("doOnCancel");
                    }
                })
                .doOnComplete(new Runnable() {
                    @Override
                    public void run() {
                        log.info("doOnComplete");
                    }
                })
                .subscribe(new DemoSubscriber());

    }
}
