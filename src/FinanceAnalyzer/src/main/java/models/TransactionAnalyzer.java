package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import utils.DateTimeFormatters;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public abstract class TransactionAnalyzer {


    public static double calculateBalance(List<Transaction> transactions) {
        double balance = 0;

        for (Transaction transaction : transactions)
            balance += transaction.getAmount();

        return balance;
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatters.DATE);
            String transactionMonth = date.format(DateTimeFormatters.MONTH_YEAR);

            if (transactionMonth.equals(monthYear)) {
                count++;
            }
        }

        return count;
    }

    public static List<Transaction> findLargestExpenses(List<Transaction> transactions, int count) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(count)
                .toList();
    }

    public static List<Transaction> findSmallestExpenses(List<Transaction> transactions, int count) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .limit(count)
                .toList();
    }

    public static List<Transaction> findTransactionsInPeriod(List<Transaction> transactions, LocalDate start, LocalDate end) {
        return transactions.stream()
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), DateTimeFormatters.DATE);
                    return !date.isBefore(start) && !date.isAfter(end);
                })
                .toList();
    }

    public static List<Transaction> findLargestExpensesInPeriod(List<Transaction> transactions, LocalDate start, LocalDate end, int count) {
        return findLargestExpenses(findTransactionsInPeriod(transactions, start, end), count);
    }

    public static List<Transaction> findSmallestExpensesInPeriod(List<Transaction> transactions, LocalDate start, LocalDate end, int count) {
        return findSmallestExpenses(findTransactionsInPeriod(transactions, start, end), count);
    }

    public static Map<String, Double> groupExpensesByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(Transaction::getDescription, TreeMap::new, Collectors.summingDouble(t -> Math.abs(t.getAmount()))));
    }
}
