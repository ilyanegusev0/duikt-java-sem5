import models.Transaction;
import models.TransactionCSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TransactionCSVReaderTest {
    @Test
    public void testReadTransactions() throws IOException {
        Path tempFile = Files.createTempFile("transactions", ".csv");

        try {
            String text = "01-01-2026,-500.0,Products\n" +
                    "02-01-2026,-1500.0,Rent\n" +
                    "03-01-2026,2000.0,Salary\n";
            Files.writeString(tempFile, text);

            String url = tempFile.toUri().toURL().toString();

            List<Transaction> transactions = TransactionCSVReader.readTransactions(url);

            Assertions.assertEquals(3, transactions.size(), "Should read exactly 3 transactions");

            Assertions.assertEquals("01-01-2026", transactions.get(0).getDate());
            Assertions.assertEquals(-500.0, transactions.get(0).getAmount(), 0.001);
            Assertions.assertEquals("Products", transactions.get(0).getDescription());

            Assertions.assertEquals("02-01-2026", transactions.get(1).getDate());
            Assertions.assertEquals(-1500.0, transactions.get(1).getAmount(), 0.001);
            Assertions.assertEquals("Rent", transactions.get(1).getDescription());

            Assertions.assertEquals("03-01-2026", transactions.get(2).getDate());
            Assertions.assertEquals(2000.0, transactions.get(2).getAmount(), 0.001);
            Assertions.assertEquals("Salary", transactions.get(2).getDescription());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testReadTransactionsFromMissingFile() {
        String url = Path.of("404").toUri().toString();

        List<Transaction> transactions = TransactionCSVReader.readTransactions(url);

        Assertions.assertNotNull(transactions, "Should not be null");
        Assertions.assertTrue(transactions.isEmpty(), "Should return empty list");
    }
}
