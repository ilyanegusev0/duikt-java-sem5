package models;

import jstyle.Text;
import utils.DateTimeFormatters;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public abstract class TransactionReportGenerator {

    public static void printAllTransactions(List<Transaction> transactions) {
        System.out.println("\n" + Text.of("Transactions (" + transactions.size() + "):").b());
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void printBalanceReport(double balance) {
        System.out.println("\n" + Text.of("Balance: ").b() + balance);
    }

    public static void printTransactionsCountByMonthReport(String mountYear, int count) {
        YearMonth yearMonth = YearMonth.parse(mountYear, DateTimeFormatters.MONTH_YEAR);
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
}
