package cn.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BaseFluxTest {
  public <T> Flux<T> appendBoomError(Flux<T> source) {
    return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
  }
}