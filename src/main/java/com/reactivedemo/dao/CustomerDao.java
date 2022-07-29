package com.reactivedemo.dao;

import com.reactivedemo.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public static void sleepExecution(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() {
        return IntStream.range(1, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println(i))
                .mapToObj(i -> new Customer(i, "customer" + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getAllCustomersReactive() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println(i))
                .map(i -> new Customer(i, "customer" + i));
    }
}
