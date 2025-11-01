package com.example.Homework.event;

import com.example.Homework.model.Customer;
import org.springframework.context.ApplicationEvent;

public class LargeDiscountEvent extends ApplicationEvent {

    private final Customer customer;
    private final double discountAmount;

    public LargeDiscountEvent(Object source, Customer customer, double discountAmount) {
        super(source);
        this.customer = customer;
        this.discountAmount = discountAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

}
