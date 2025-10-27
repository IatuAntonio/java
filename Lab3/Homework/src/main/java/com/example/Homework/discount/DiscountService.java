package com.example.Homework.discount;

import com.example.Homework.model.Customer;
import com.example.Homework.model.Order;

public interface DiscountService {
    double calculateDiscount(Customer customer, Order order);
}
