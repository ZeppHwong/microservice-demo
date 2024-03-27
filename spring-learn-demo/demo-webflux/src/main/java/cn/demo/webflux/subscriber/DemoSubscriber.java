package cn.demo.webflux.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class DemoSubscriber implements Subscriber<Integer> {

    @Override
    public void onSubscribe(Subscription s) {
        log.info("onSubscribe request max");
        s.request(Integer.MAX_VALUE);
    }


    @Override
    public void onNext(Integer integer) {
        log.info("onNext get data:{}",integer);

    }

    @Override
    public void onError(Throwable t) {
        log.info("onError get data error:",t);
    }

    @Override
    public void onComplete() {
        log.info("onComplete");
    }
}
