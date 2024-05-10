package cn.demo;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class SimpleFluxTest extends BaseFluxTest {
  @Test
  public void testAppendBoomError() {
    Flux<String> source = Flux.just("thing1", "thing2");
    StepVerifier.create(appendBoomError(source))
            .expectNext("thing1")
            .expectNext("thing2")
            .expectErrorMessage("boom")
            .verify();
  }

  @Test
  public void testMonoDelay() {
    StepVerifier.withVirtualTime(() -> Mono.delay(Duration.ofDays(1)))
            .expectSubscription()
            .expectNoEvent(Duration.ofDays(1))
            .expectNext(0L)
            .verifyComplete();
  }
}
