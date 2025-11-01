package com.example.Homework.rules;


import com.example.Homework.model.Customer;
import com.example.Homework.model.Order;
import org.springframework.stereotype.Component;

@Component
public class HighValueOrderRule implements DiscountRule {

    @Override
    public boolean matches(Customer customer, Order order) {
        return order.getTotalAmount() > 1000;
    }

    @Override
    public double getDiscount(Customer customer, Order order) {
        return 50.0;
    }

}
