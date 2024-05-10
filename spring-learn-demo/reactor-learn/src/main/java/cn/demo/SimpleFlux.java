package cn.demo;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class SimpleFlux extends BaseFlux {
    public static void main(String[] args) {
        FluxCreate();
        OtherFlexCreate();
        FluxError();
        FluxComplete();
        ImplBaseSubscriber();
        CustomizingOriginalRequest();
    }

    private static void CustomizingOriginalRequest() {
        Flux.range(1, 10)
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new BaseSubscriber<Integer>() {

                    @Override
                    public void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    public void hookOnNext(Integer integer) {
                        System.out.println("Cancelling after having received " + integer);
                        cancel();
                    }
                });
    }

    private static void ImplBaseSubscriber() {
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(ss);
    }

    public static class SampleSubscriber<T> extends BaseSubscriber<T> {

        @Override
        public void hookOnSubscribe(Subscription subscription) {
            System.out.println("Subscribed");
            request(1);
        }

        @Override
        public void hookOnNext(T value) {
            System.out.println(value);
            request(1);
        }
    }

    private static void OtherFlexCreate() {
        Mono<String> noData = Mono.empty();
        noData.subscribe(System.out::println);
        Mono<String> data = Mono.just("foo");
        data.subscribe(System.out::println);
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
        numbersFromFiveToSeven.subscribe(System.out::println);
    }

    private static void FluxCreate() {
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        printFlux(seq1);

        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);
        printFlux(seq2);
    }

    private static void FluxError() {
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        printFluxOnError(ints);
    }

    private static void FluxComplete() {
        Flux<Integer> ints = Flux.range(1, 4);
        printFluxOnError(ints);
    }
}
