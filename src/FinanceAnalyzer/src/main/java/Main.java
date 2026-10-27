import models.Transaction;
import models.TransactionAnalyzer;
import models.TransactionCSVReader;
import models.TransactionReportGenerator;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main() {
        final String FILE_PATH = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(FILE_PATH);

        TransactionReportGenerator.printAllTransactions(transactions);

        double balance = TransactionAnalyzer.calculateBalance(transactions);
        TransactionReportGenerator.printBalanceReport(balance);

        String monthYear = "01-2024";
        int transactionCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonthReport(monthYear, transactionCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions, 10);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 3, 31);
        List<Transaction> transactionsInPeriod = TransactionAnalyzer.findTransactionsInPeriod(transactions, start, end);
        TransactionReportGenerator.printTransactionsInPeriod(transactionsInPeriod, start, end);

    }
}
