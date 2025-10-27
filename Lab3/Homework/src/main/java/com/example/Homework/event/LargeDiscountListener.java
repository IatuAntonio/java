package com.example.Homework.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LargeDiscountListener {

    @EventListener
    public void handleLargeDiscount(LargeDiscountEvent event) {
        System.out.printf("[LargeDiscountListener] Large discount detected: %.2f for customer %s%n",
                event.getDiscountAmount(),
                event.getCustomer().getName()
        );
    }

}
