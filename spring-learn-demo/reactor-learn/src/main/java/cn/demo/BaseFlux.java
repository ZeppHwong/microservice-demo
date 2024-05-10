package cn.demo;

import reactor.core.publisher.Flux;

public class BaseFlux {
    protected static void printSeparateLine() {
        System.out.println("************************");
    }

    protected static <T> void printFlux(Flux<T> flux) {
        flux.subscribe(System.out::println);
        printSeparateLine();
    }

    protected static <T> void printFluxOnError(Flux<T> flux) {
        flux.subscribe(System.out::println, error -> System.err.println("Error: " + error));
        printSeparateLine();
    }

    protected static <T> void printFluxOnDone(Flux<T> flux) {
        flux.subscribe(System.out::println,
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Done"));
        printSeparateLine();
    }
}
