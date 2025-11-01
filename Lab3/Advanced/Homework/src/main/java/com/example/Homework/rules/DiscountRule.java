package com.example.Homework.rules;

import com.example.Homework.model.Customer;
import com.example.Homework.model.Order;

public interface DiscountRule {

    boolean matches(Customer customer, Order order);

    double getDiscount(Customer customer, Order order);

}
