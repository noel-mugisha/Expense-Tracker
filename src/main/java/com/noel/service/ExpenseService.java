package com.noel.service;

import com.noel.model.Expense;
import com.noel.repository.ExpenseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void getMonthlyTotals() {
        Map<String, Double> monthlyTotals = expenseRepository.getExpenses().stream()
                .collect(Collectors.groupingBy( expense -> expense.getDate().getMonth().name(),
                        Collectors.summingDouble(Expense::getAmount)));

        monthlyTotals.forEach((key, value) -> System.out.println(key + " --> " + value + " RWF"));
    }

    public void getCategoryWiseTotals() {
        Map<String, Double> categoryTotals = expenseRepository.getExpenses().stream()
                .collect(Collectors.groupingBy(Expense::getCategory, Collectors.summingDouble(Expense::getAmount)));

        categoryTotals.forEach((key, value) -> System.out.println(key + " --> " + value + " RWF"));
    }

}
