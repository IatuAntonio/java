package com.example.Homework.discount;

import com.example.Homework.model.Customer;
import com.example.Homework.model.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"nodiscount", "default"})
public class NoDiscountService implements DiscountService {

    @Override
    public double calculateDiscount(Customer customer, Order order) {
        System.out.println("[NoDiscountService] No discount applied...");
        return 0.0;
    }

}
