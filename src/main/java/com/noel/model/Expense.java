package com.noel.model;

import java.time.LocalDate;

public class Expense {
    private static int counter = 0;
    private final int id;
    private final String category;
    private final double amount;
    private final LocalDate date;

    public Expense(String category, double amount, LocalDate date) {
        this.id = ++counter;
        this.category = category;
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
