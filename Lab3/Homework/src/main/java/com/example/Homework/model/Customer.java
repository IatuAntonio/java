package com.example.Homework.model;


public class Customer {

    private Long id;
    private String name;
    private boolean eligibleForDiscount;
    private String loyaltyLevel;

    public Customer(Long id, String name, boolean eligibleForDiscount, String loyaltyLevel) {
        this.id = id;
        this.name = name;
        this.eligibleForDiscount = eligibleForDiscount;
        this.loyaltyLevel = loyaltyLevel;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEligibleForDiscount() {
        return eligibleForDiscount;
    }

    public String getLoyaltyLevel() {
        return loyaltyLevel;
    }
}
