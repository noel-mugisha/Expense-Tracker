package com.noel.repository;

import com.noel.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {
    private static List<Expense> expenses = new ArrayList<>();

    public void saveExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenses;
    }
}
