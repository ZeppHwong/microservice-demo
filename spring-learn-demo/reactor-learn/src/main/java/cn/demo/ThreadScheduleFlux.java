package cn.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class ThreadScheduleFlux extends BaseFlux {

  public static void main(String[] args) throws InterruptedException {
//    MonoThread();
    PublishOnMethod();
    SubscribeOnMethod();
    HandlerErrors();
  }

  private static void HandlerErrors() {
    Flux<String> stringFlux = Flux.just(1, 2, 0)
            .map(i -> "100 / " + i + " = " + (100 / i)) //this triggers an error with 0
            .onErrorReturn("Divided by zero :(");
    printFlux(stringFlux);
  }

  private static void SubscribeOnMethod() {
    Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

    final Flux<String> flux = Flux
            .range(1, 2)
            .map(i -> 10 + i)
            .subscribeOn(s)
            .map(i -> "value " + i);

    new Thread(() -> flux.subscribe(System.out::println)).start();
  }

  private static void PublishOnMethod() {
    Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

    final Flux<String> flux = Flux
            .range(1, 2)
            .map(i -> 10 + i)
            .publishOn(s)
            .map(i -> "value " + i);
    new Thread(() -> flux.subscribe(System.out::println)).start();
  }

  private static void MonoThread() throws InterruptedException {
    final Mono<String> mono = Mono.just("hello ");
    Thread t = new Thread(() -> mono
            .map(msg -> msg + "thread ")
            .subscribe(v ->
                    System.out.println(v + Thread.currentThread().getName())
            )
    );
    t.start();
    t.join();
  }


}
