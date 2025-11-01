package com.example.Homework.aspect;

import com.example.Homework.exception.CustomerNotEligibleException;
import com.example.Homework.model.Customer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DiscountEligibilityAspect {

    @Before("execution(* com.example.Homework.rules.DiscountRule.matches(..))")
    public void checkEligibility(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length >= 1 && args[0] instanceof Customer customer) {
            if (customer == null) {
                throw new CustomerNotEligibleException("Customer not found!");
            }
            if (!customer.isEligibleForDiscount()) {
                throw new CustomerNotEligibleException("Customer '" + customer.getName() + "' is not eligible for discounts!");
            }
        }
    }

}
