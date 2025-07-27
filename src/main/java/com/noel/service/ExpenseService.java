package com.noel.service;

import com.noel.model.Expense;
import com.noel.repository.ExpenseRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseService {
    private final ExpenseRepository expenseRepository = new ExpenseRepository();

    // method of adding an expense
    public void addExpense(Expense expense) throws IllegalArgumentException{
        expenseRepository.saveExpense(expense);
        System.out.println("Successfully added expense: " + expense);

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

    public void generateReport () throws IOException {
        List<Expense> expenses = expenseRepository.getExpenses();
        Map<String, Double> monthlyTotals = expenseRepository.getExpenses().stream()
                .collect(Collectors.groupingBy( expense -> expense.getDate().getMonth().name(),
                        Collectors.summingDouble(Expense::getAmount)));
        Map<String, Double> categoryTotals = expenseRepository.getExpenses().stream()
                .collect(Collectors.groupingBy(Expense::getCategory, Collectors.summingDouble(Expense::getAmount)));

        try(PrintWriter  writer = new PrintWriter("REPORT.txt")) {
            // Write monthly totals
            writer.println("=== MONTHLY TOTALS ===");
            monthlyTotals.forEach((month, amount) ->
                    writer.printf("%s --> %.2f RWF%n", month, amount));

            // Write category-wise totals
            writer.println("\n=== CATEGORY TOTALS ===");
            categoryTotals.forEach((category, amount) ->
                    writer.printf("%s --> %.2f RWF%n", category, amount));

            // write all expenses report
            writer.println("\n=== ALL EXPENSES ===");
            expenses.forEach(writer::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
