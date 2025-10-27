package com.example.Homework.repository;

import com.example.Homework.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepository {

    private final Map<Long, Customer> customers = new HashMap<>();

    public CustomerRepository() {
        customers.put(1L, new Customer(1L, "Antonio", true, "LOYAL"));
        customers.put(2L, new Customer(2L, "Bobitza", true, "REGULAR"));
        customers.put(3L, new Customer(3L, "Camelia", false, "REGULAR"));
    }

    public Customer findById(Long id) {
        return customers.get(id);
    }

}
