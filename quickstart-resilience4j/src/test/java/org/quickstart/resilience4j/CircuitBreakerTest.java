package org.quickstart.resilience4j;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.junit.Test;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.vavr.control.Try;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2019/11/16 09:49
 */
public class CircuitBreakerTest {

  RemoteService backendService = new RemoteServiceImpl();

  @Test
  public void testCircuitBreaker() {
    // Create a CircuitBreaker (use default configuration)
    CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom().enableAutomaticTransitionFromOpenToHalfOpen().build();
    CircuitBreaker circuitBreaker = CircuitBreaker.of("backendName", circuitBreakerConfig);

    IntStream.rangeClosed(1, 5)//
        .parallel()//
        .forEach(i -> {
          String result = circuitBreaker.executeSupplier(() -> backendService.doSomethingWithArgs("world"));
          System.out.println(result);
        });
  }

  @Test
  public void testTimelimiter() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    TimeLimiterConfig config = TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(600)).cancelRunningFuture(true).build();
    TimeLimiter timeLimiter = TimeLimiter.of(config);

    Supplier<Future<String>> futureSupplier = () -> {
      return executorService.submit(backendService::doSomethingThrowException);
    };
    Callable<String> restrictedCall = TimeLimiter.decorateFutureSupplier(timeLimiter, futureSupplier);
    Try.of(restrictedCall::call).onFailure(throwable -> System.out.println("We might have timed out or the circuit breaker has opened."));
  }

  /**
   * A Bulkhead can be used to limit the amount of parallel executions
   */
  @Test
  public void testBulkhead() {
    Bulkhead bulkhead = Bulkhead.of("test", BulkheadConfig.custom().maxConcurrentCalls(1).build());
    Supplier<String> decoratedSupplier = Bulkhead.decorateSupplier(bulkhead, backendService::doSomethingSlowly);
    IntStream.rangeClosed(1, 2)//
        .parallel()//
        .forEach(i -> {//
          String result = Try.ofSupplier(decoratedSupplier).recover(throwable -> "Hello from Recovery").get();
          System.out.println(result);
        });

  }

  @Test
  public void testRateLimiter() {
    // Create a custom RateLimiter configuration
    RateLimiterConfig config =
        RateLimiterConfig.custom().timeoutDuration(Duration.ofMillis(100)).limitRefreshPeriod(Duration.ofSeconds(1)).limitForPeriod(1).build();
    // Create a RateLimiter
    RateLimiter rateLimiter = RateLimiter.of("backendName", config);

    // Decorate your call to BackendService.doSomething()
    Supplier<String> restrictedSupplier = RateLimiter.decorateSupplier(rateLimiter, backendService::doSomething);

    IntStream.rangeClosed(1, 5)//
        .parallel()//
        .forEach(i -> {
          Try<String> aTry = Try.ofSupplier(restrictedSupplier);
          System.out.println(aTry.isSuccess());
        });
  }

  @Test
  public void testFallback() {
    // Execute the decorated supplier and recover from any exception
    String result = Try.ofSupplier(() -> backendService.doSomethingThrowException()).recover(throwable -> "Hello from Recovery").get();
    System.out.println(result);
  }

  @Test
  public void testCircuitBreakerAndFallback() {
    CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("backendName");
    Supplier<String> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, backendService::doSomethingThrowException);
    String result = Try.ofSupplier(decoratedSupplier).recover(throwable -> "Hello from Recovery").get();
    System.out.println(result);
  }

  @Test
  public void testRetry() {
    CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("backendName");
    // Create a Retry with at most 3 retries and a fixed time interval between retries of 500ms
    Retry retry = Retry.ofDefaults("backendName");

    // Decorate your call to BackendService.doSomething() with a CircuitBreaker
    Supplier<String> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, backendService::doSomething);

    // Decorate your call with automatic retry
    decoratedSupplier = Retry.decorateSupplier(retry, decoratedSupplier);

    // Execute the decorated supplier and recover from any exception
    String result = Try.ofSupplier(decoratedSupplier).recover(throwable -> "Hello from Recovery").get();
    System.out.println(result);
  }

  @Test
  public void test() {
    CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

    CircuitBreakerConfig config = CircuitBreakerConfig.custom().failureRateThreshold(20).ringBufferSizeInClosedState(5).build();
    CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
    CircuitBreaker circuitBreaker = registry.circuitBreaker("my");

    RemoteService service = new RemoteServiceImpl();
    Function<Integer, Integer> decorated = CircuitBreaker.decorateFunction(circuitBreaker, service::process);

    // when(service.process(any(Integer.class))).thenThrow(new RuntimeException());

    for (int i = 0; i < 10; i++) {
      try {
        decorated.apply(i);
      } catch (Exception e) {
        e.printStackTrace();

      }
    }

    // verify(service, times(5)).process(any(Integer.class));

  }

}
