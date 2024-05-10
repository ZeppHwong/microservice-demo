package cn.demo;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

public class SequenceFlux extends BaseFlux {
    public static void main(String[] args) {
        SynchronousGenerate();
        GenerateMutableObject();
        GenerateMutableObjectWithConsumer();

    }

    private static void GenerateMutableObjectWithConsumer() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3*i);
                    if (i == 10) sink.complete();
                    return state;
                }, (state) -> System.out.println("state: " + state));
        printFlux(flux);
    }

    private static void GenerateMutableObject() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                });
        printFlux(flux);
    }

    private static void SynchronousGenerate() {
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });
        printFlux(flux);
    }
}
