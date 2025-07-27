package com.noel.UI;

import com.noel.model.Expense;
import com.noel.service.ExpenseService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleUI {
    private static final ExpenseService expenseService = new ExpenseService();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void start() {
        ConsoleUI ui = new ConsoleUI();
        ui.run();
    }

    private void run() {
        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    getAllExpenses();
                    break;
                case 3:
                    getMonthlyTotals();
                    break;
                case 4:
                    getCategoryWiseTotals();
                    break;
                case 5:
                    generateReport();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== Expense Tracker Menu =====");
        System.out.println("1. Add an expense");
        System.out.println("2. View all expenses");
        System.out.println("3. View monthly totals");
        System.out.println("4. View category-wise totals");
        System.out.println("5. Generate report");
        System.out.println("6. Exit");
    }

    private void addExpense() {
        System.out.println("\n=== Add New Expense ===");
        String category = getStringInput("Enter category: ");
        double amount = getDoubleInput("Enter amount (RWF): ");
        LocalDate date = getDateInput("Enter date (YYYY-MM-DD): ");

        try {
            Expense expense = new Expense(category, amount, date);
            expenseService.addExpense(expense);
            System.out.println("Expense added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getAllExpenses() {
        System.out.println("\n=== All Expenses ===");
        expenseService.getAllExpenses();
    }

    private void getMonthlyTotals() {
        System.out.println("\n=== Monthly Totals ===");
        expenseService.getMonthlyTotals();
    }

    private void getCategoryWiseTotals() {
        System.out.println("\n=== Category-Wise Totals ===");
        expenseService.getCategoryWiseTotals();
    }

    private void generateReport() {
        System.out.println("\nGenerating report...");
        try {
            expenseService.generateReport();
            System.out.println("Report generated successfully as REPORT.txt");
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }

    // Helper methods for input validation
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
            }
        }
    }

    private static LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return LocalDate.parse(scanner.nextLine(), dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }
}