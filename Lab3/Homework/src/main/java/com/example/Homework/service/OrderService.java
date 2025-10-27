package com.example.Homework.service;

import com.example.Homework.event.LargeDiscountEvent;
import com.example.Homework.model.Customer;
import com.example.Homework.model.Order;
import com.example.Homework.discount.DiscountService;
import com.example.Homework.repository.CustomerRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final DiscountService discountService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public OrderService(CustomerRepository customerRepository, DiscountService discountService, ApplicationEventPublisher eventPublisher) {
        this.customerRepository = customerRepository;
        this.discountService = discountService;
        this.eventPublisher = eventPublisher;
    }

    public void processOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomerId());
        if (customer == null) {
            System.out.println("[OrderService] Customer not found...");
            return;
        }

        try {
            double discount = discountService.calculateDiscount(customer, order);
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
//        String methodName = discountService.getClass().getSimpleName();
        String methodName = discountService.getClass().getSimpleName().replaceAll("\\$\\$.*", "");

        System.out.printf("[OrderService] Method: %s | Customer: %s | Discount: %.2f%n",
                methodName, customer.getName(), discountAmount);
    }

}
