package com.example.Homework.model;


public class Order {

    private Long id;
    private Long customerId;
    private double totalAmount;
    private double discountApplied;
    private double finalPrice;

    public Order(Long id, Long customerId, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.discountApplied = 0.0;
        this.finalPrice = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public double getDiscountApplied() {
        return discountApplied;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void applyDiscount(double discountAmount) {
        this.discountApplied = discountAmount;
        this.finalPrice = this.totalAmount - discountAmount;

        if (this.finalPrice < 0) {
            this.finalPrice = 0;
        }
    }
}
