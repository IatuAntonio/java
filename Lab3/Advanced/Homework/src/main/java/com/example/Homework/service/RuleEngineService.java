package com.example.Homework.service;

import com.example.Homework.model.Customer;
import com.example.Homework.model.Order;
import com.example.Homework.rules.DiscountRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleEngineService {

    private final List<DiscountRule> rules;

    public RuleEngineService(List<DiscountRule> rules) {
        this.rules = rules;
    }

    public double calculateDiscount(Customer customer, Order order) {
        double totalDiscount = 0.0;

        for (DiscountRule rule : rules) {
            if (rule.matches(customer, order)) {
                double discount = rule.getDiscount(customer, order);
                totalDiscount += discount;
                System.out.printf("[RuleEngine] Applied %s: %.2f%n", rule.getClass().getSimpleName(), discount);
            }
        }
        return totalDiscount;
    }

}
