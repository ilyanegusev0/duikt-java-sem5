import models.Transaction;
import models.TransactionAnalyzer;
import models.TransactionCSVReader;
import models.TransactionReportGenerator;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main() {
        final String FILE_PATH = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(FILE_PATH);

        //printAllTransactions
        TransactionReportGenerator.printAllTransactions(transactions);

        //printBalanceReport
        double balance = TransactionAnalyzer.calculateBalance(transactions);
        TransactionReportGenerator.printBalanceReport(balance);

        // printTransactionsCountByMonthReport
        String monthYear = "01-2024";
        int transactionCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonthReport(monthYear, transactionCount);

        // printLargestExpensesReport
        List<Transaction> largestExpenses = TransactionAnalyzer.findLargestExpenses(transactions, 10);
        TransactionReportGenerator.printLargestExpensesReport(largestExpenses);
        List<Transaction> smallestExpenses = TransactionAnalyzer.findSmallestExpenses(transactions, 10);
        TransactionReportGenerator.printSmallestExpensesReport(smallestExpenses);

        // printTransactionsInPeriod
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 3, 31);
        List<Transaction> transactionsInPeriod = TransactionAnalyzer.findTransactionsInPeriod(transactions, start, end);
        TransactionReportGenerator.printTransactionsInPeriod(transactionsInPeriod, start, end);

        // printLargestExpensesInPeriod
        List<Transaction> largestExpensesInPeriod = TransactionAnalyzer.findLargestExpensesInPeriod(transactions, start, end, 100);
        TransactionReportGenerator.printLargestExpensesInPeriod(largestExpensesInPeriod, start, end);

        // printSmallestExpensesInPeriod
        List<Transaction> smallestExpensesInPeriod = TransactionAnalyzer.findSmallestExpensesInPeriod(transactions, start, end, 100);
        TransactionReportGenerator.printSmallestExpensesInPeriod(smallestExpensesInPeriod, start, end);

        //printExpensesByCategoryReport
        Map<String, Double> categories = TransactionAnalyzer.groupExpensesByCategory(transactions);
        TransactionReportGenerator.printExpensesByCategoryReport(categories);

        // printExpensesByMonthReport
        Map<YearMonth, Double> months = TransactionAnalyzer.groupExpensesByMonth(transactions);
        TransactionReportGenerator.printExpensesByMonthReport(months);
    }
}
