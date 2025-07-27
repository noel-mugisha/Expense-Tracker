Expense Tracker
A Java application for tracking personal expenses with categorization and reporting features.
Features

Add and validate expenses
Monthly expense summaries
Category-wise totals (Food, Rent, Transport)
Export reports to text files
Input validation with exception handling

Technologies

Java 8+ (Streams, Lambdas)
Collections (Map, List)
File I/O
Exception Handling

How to Run
bashjavac *.java
java Main
Usage
java// Add expense
tracker.addExpense("Food", 25.50, "2024-01-15");

// Get monthly totals
Map<String, Double> monthlyTotals = tracker.getMonthlyTotals();

// Export report
tracker.exportReport("report.txt");
Project Structure

Expense.java - Expense model class
ExpenseTracker.java - Main logic with Streams processing
InvalidExpenseException.java - Custom exception handling
Main.java - Application entry point
