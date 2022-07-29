package com.reactivedemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    /**
     * Mono is a publisher class; we subscribe the mono object.
     * the methods onSubscribe(), request(),onNext(), onComplete() gets called
     */
    @Test
    public void testMonoSimple() {
        Mono<String> testData = Mono.just("testData").log();
        testData.subscribe(System.out::println);
    }

    /**
     * In case of exception raised by mono then the onNext(), onComplete() methods will not be called,
     * onError() will be called
     */
    @Test
    public void testMonoWithExcepton() {
        Mono<Object> testData = Mono.just("TestData")
                .then(Mono.error(new RuntimeException("Exceptioin")))
                .log();
        testData.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }
    @Test
    public void testFluxData(){
        Flux<String> stringFlux = Flux.just("testData1", "testData2", "testData3")
                .concatWith(Flux.error(new RuntimeException()))
                .log();
        stringFlux.subscribe(System.out::println, (e)-> System.out.println(e.getMessage()));
    }
}
