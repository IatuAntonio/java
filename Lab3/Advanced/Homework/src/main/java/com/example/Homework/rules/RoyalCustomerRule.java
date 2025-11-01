package com.example.Homework.rules;

import com.example.Homework.model.Customer;
import com.example.Homework.model.Order;
import org.springframework.stereotype.Component;

@Component
public class RoyalCustomerRule implements DiscountRule {

    @Override
    public boolean matches(Customer customer, Order order) {
        return customer.getLoyaltyLevel().equalsIgnoreCase("ROYAL");
    }

    @Override
    public double getDiscount(Customer customer, Order order) {
        return order.getTotalAmount() * 0.20;
    }

}
