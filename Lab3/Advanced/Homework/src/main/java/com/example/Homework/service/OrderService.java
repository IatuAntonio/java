package com.example.Homework.service;

import org.example.audit.annotation.Audited;

import com.example.Homework.event.LargeDiscountEvent;
import com.example.Homework.model.Customer;
import com.example.Homework.model.Order;
import com.example.Homework.repository.CustomerRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final RuleEngineService ruleEngineService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public OrderService(CustomerRepository customerRepository, RuleEngineService ruleEngineService, ApplicationEventPublisher eventPublisher) {
        this.customerRepository = customerRepository;
        this.ruleEngineService = ruleEngineService;
        this.eventPublisher = eventPublisher;
    }

    @Audited
    public void processOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomerId());
        if (customer == null) {
            System.out.println("[OrderService] Customer not found...");
            return;
        }

        try {
            double discount = ruleEngineService.calculateDiscount(customer, order);
            order.applyDiscount(discount);
            logDiscountDetails(customer, discount);
            if (discount > 100.0) {
                eventPublisher.publishEvent(new LargeDiscountEvent(this, customer, discount));
            }
        } catch (Exception e) {
            System.out.println("[OrderService] Error calculating discount: " + e.getMessage());
        }
    }

    private void logDiscountDetails(Customer customer, double discountAmount) {
        System.out.printf("[OrderService] Customer: %s | Discount: %.2f%n", customer.getName(), discountAmount);
    }

}
