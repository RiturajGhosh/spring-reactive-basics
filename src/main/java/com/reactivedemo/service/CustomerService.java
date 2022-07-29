package com.reactivedemo.service;

import com.reactivedemo.dao.CustomerDao;
import com.reactivedemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getAllCustomer() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getAllCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Time spent" + (end - start));
        return customers;
    }

    public Flux<Customer> getAllCustomerReactive() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getAllCustomersReactive();
        long end = System.currentTimeMillis();
        System.out.println("Time spent" + (end - start));
        return customers;
    }
}
