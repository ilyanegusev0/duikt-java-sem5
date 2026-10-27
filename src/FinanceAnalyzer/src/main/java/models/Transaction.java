package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private String date;
    private double amount;
    private String description;

    @Override
    public String toString() {
        return "Transaction(" +
                "Date: " + date +
                "; Amount: " + amount +
                "; Description: " + description +
                ")";
    }
}
