package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {
    public static List<Transaction> readTransactions(String path) {
        List<Transaction> transactions = new ArrayList<>();

        try {
            URL url = new URL(path);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");

                    String date = values[0];
                    double amount = Double.parseDouble(values[1]);
                    String description = values[2];

                    Transaction transaction = new Transaction(date, amount, description);
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
