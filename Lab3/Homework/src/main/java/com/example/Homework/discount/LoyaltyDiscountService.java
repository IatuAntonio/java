package com.example.Homework.discount;


import com.example.Homework.model.Customer;
import com.example.Homework.model.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("loyal")
public class LoyaltyDiscountService implements DiscountService {

    @Override
    public double calculateDiscount(Customer customer, Order order) {
        double discount = order.getTotalAmount() * 0.10;
        System.out.println("[LoyaltyDiscountService] Applied 10% loyalty discount...");
        return discount;
    }

}
