import models.Transaction;
import models.TransactionAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TransactionAnalyzerTest {
    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        transactions = new ArrayList<>();
        transactions.add(new Transaction("01-01-2026", -500.0, "Продукти"));
        transactions.add(new Transaction("02-01-2026", -1500.0, "Оренда"));
        transactions.add(new Transaction("03-01-2026", -200.0, "Кава"));
        transactions.add(new Transaction("04-01-2026", 2000.0, "Зарплата"));
        transactions.add(new Transaction("05-01-2026", -3000.0, "Техніка"));
        transactions.add(new Transaction("06-01-2026", -50.0, "Транспорт"));
        transactions.add(new Transaction("07-02-2026", -800.0, "Комуналка"));
        transactions.add(new Transaction("08-02-2026", -50.0, "Перекус"));
        transactions.add(new Transaction("09-02-2026", -1200.0, "Паливо"));
        transactions.add(new Transaction("10-02-2026", -450.0, "Кіно"));
        transactions.add(new Transaction("11-03-2026", -3000.0, "Страховка"));
        transactions.add(new Transaction("12-03-2026", -150.0, "Книги"));
    }

    @Test
    public void testCalculateBalance() {
        double result = TransactionAnalyzer.calculateBalance(transactions);

        Assertions.assertEquals(-8900.0, result, "The total amount calculation is incorrect");
    }

    @Test
    public void testCountTransactionsByMonth() {
        int countJan = TransactionAnalyzer.countTransactionsByMonth(transactions, "01-2026");
        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2026");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2026");

        Assertions.assertEquals(6, countJan, "Total transactions for January is incorrect");
        Assertions.assertEquals(4, countFeb, "Total transactions for February is incorrect");
        Assertions.assertEquals(2, countMar, "Total transactions for March is incorrect");
    }

    @Test
    public void testFindTopExpenses() {
        List<Transaction> top10 = TransactionAnalyzer.findTopExpenses(transactions, 10);

        Assertions.assertEquals(10, top10.size(), "Should return exactly 10 expenses");

        Assertions.assertEquals(-3000.0, top10.getFirst().getAmount(), "The first element must be the largest expense");
        Assertions.assertEquals(-50.0, top10.getLast().getAmount(), "The last element must be the lowest expense");

        boolean containsPositive = top10.stream().anyMatch(t -> t.getAmount() > 0);
        Assertions.assertFalse(containsPositive, "Top expenses list must not contain any positive income transactions");
    }
}
