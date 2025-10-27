package com.example.Homework.discount;

import com.example.Homework.model.Customer;
import com.example.Homework.model.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("highvalue")
public class HighValueDiscountService implements DiscountService {

    @Override
    public double calculateDiscount(Customer customer, Order order) {
        if (order.getTotalAmount() > 500) {
            System.out.println("[HighValueDiscountService] Applied $50 high-value discount...");
            return 50.0;
        }
        System.out.println("[HighValueDiscountService] No discount applied...");
        return 0.0;
    }

}
