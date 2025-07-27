package com.noel.service;

import com.noel.model.Expense;
import com.noel.repository.ExpenseRepository;

import java.util.List;

public class ExpenseService {
    private final ExpenseRepository expenseRepository = new ExpenseRepository();

    // method of adding an expense
    public void addExpense(Expense expense) throws IllegalArgumentException{
        try {
            expenseRepository.saveExpense(expense);
            System.out.println("Successfully added expense: " + expense);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void getAllExpenses() {
        List<Expense> allExpenses = expenseRepository.getExpenses();
        allExpenses.forEach(System.out::println);
    }

}
