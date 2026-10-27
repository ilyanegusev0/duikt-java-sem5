package models;

import jstyle.Text;
import utils.DateTimeFormatters;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {
    private static final double UNIT_VALUE = 1000;

    public static void printAllTransactions(List<Transaction> transactions) {
        System.out.println("\n" + Text.of("Transactions (" + transactions.size() + "):").b());
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void printBalanceReport(double balance) {
        System.out.println("\n" + Text.of("Balance: ").b() + balance);
    }

    public static void printTransactionsCountByMonthReport(String monthYear, int count) {
        YearMonth yearMonth = YearMonth.parse(monthYear, DateTimeFormatters.MONTH_YEAR);
        String formattedDate = yearMonth.format(DateTimeFormatters.MONTH_YEAR_DISPLAY);
        System.out.println("\n" + Text.of("Total transaction for " + Text.of(formattedDate).i() + ": ").b() + count);
    }

    public static void printLargestExpensesReport(List<Transaction> largestExpenses) {
        System.out.println("\n" + Text.of("Top " + largestExpenses.size() + " largest expenses:").b());
        for (Transaction expense : largestExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printSmallestExpensesReport(List<Transaction> smallestExpenses) {
        System.out.println("\n" + Text.of("Top " + smallestExpenses.size() + " smallest expenses:").b());
        for (Transaction expense : smallestExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printTransactionsInPeriod(List<Transaction> transactionsInPeriod, LocalDate start, LocalDate end) {
        String formattedStart = start.format(DateTimeFormatters.DATE_DISPLAY);
        String formattedEnd = end.format(DateTimeFormatters.DATE_DISPLAY);
        System.out.println("\n" + Text.of("Transactions from " + Text.of(formattedStart).i() + " to " + Text.of(formattedEnd).i() + " (" + transactionsInPeriod.size() + "):").b());
        for (Transaction transaction : transactionsInPeriod) {
            System.out.println(transaction);
        }
    }

    public static void printLargestExpensesInPeriod(List<Transaction> largestExpensesInPeriod, LocalDate start, LocalDate end) {
        String formattedStart = start.format(DateTimeFormatters.DATE_DISPLAY);
        String formattedEnd = end.format(DateTimeFormatters.DATE_DISPLAY);

        System.out.println("\n" + Text.of("Top " + largestExpensesInPeriod.size() + " largest expenses from " + formattedStart + " to " + formattedEnd + ":").b());
        for (Transaction expense : largestExpensesInPeriod) {
            System.out.println(expense);
        }
    }

    public static void printSmallestExpensesInPeriod(List<Transaction> smallestExpensesInPeriod, LocalDate start, LocalDate end) {
        String formattedStart = start.format(DateTimeFormatters.DATE_DISPLAY);
        String formattedEnd = end.format(DateTimeFormatters.DATE_DISPLAY);

        System.out.println("\n" + Text.of("Top " + smallestExpensesInPeriod.size() + " smallest expenses from " + formattedStart + " to " + formattedEnd + ":").b());
        for (Transaction expense : smallestExpensesInPeriod) {
            System.out.println(expense);
        }
    }

    public static void printExpensesByCategoryReport(Map<String, Double> categories) {
        System.out.println("\n" + Text.of("Expenses grouped by category:").b());
        categories.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<String, Double>::getValue).reversed())
                .forEach(e -> System.out.printf("%-25s %10.2f | %s%n",
                        e.getKey(),
                        e.getValue(),
                        buildBar(e.getValue()))
                );
    }

    public static void printExpensesByMonthReport(Map<YearMonth, Double> months) {
        System.out.println("\n" + Text.of("Expenses grouped by months:").b());
        months.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<YearMonth, Double>::getValue).reversed())
                .forEach(e -> System.out.printf("%-10s %10.2f | %s%n",
                        e.getKey().format(DateTimeFormatters.MONTH_YEAR_DISPLAY),
                        e.getValue(),
                        buildBar(e.getValue()))
                );
    }

    private static String buildBar(double value) {
        if (value <= 0) {
            return "";
        }

        int asterisks = (int) Math.max(1, Math.round(value / UNIT_VALUE));
        return "*".repeat(asterisks);
    }
}
