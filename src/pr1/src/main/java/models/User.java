package models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private static int idCounter = 1;

    private int id = idCounter++;
    private int balance = 0;
    private List<Game> library = new ArrayList<>();
    private List<Game> cart = new ArrayList<>();

    private final String name;
    private final String description;
    private final int level;

    @Override
    public String toString() {
        String gameTitles = " - " + String.join("\n - ", library.stream().map(Game::getTitle).toList());

        return String.format("""
                        \u001B[1m%s [%d]\u001B[0m
                        
                        %s
                        
                        \u001B[1mBALANCE:\u001B[0m %d₴
                        \u001B[1mLEVEL:\u001B[0m %d
                        
                        \u001B[1mLIBRARY (%d items):\u001B[0m
                        %s""",
                name, id, description, balance, level, library.size(), gameTitles);
    }

    public boolean addFunds(int amount) {
        if (amount <= 0)
            return false;
        balance += amount;
        return true;
    }

    public boolean withdrawFunds(int amount) {
        if (amount <= 0 || balance < amount)
            return false;
        balance -= amount;
        return true;
    }

    public boolean addToCart(Game game) {
        if (game == null || !game.isAvailable())
            return false;
        if (cart.contains(game) || library.contains(game))
            return false;
        return cart.add(game);
    }

    public boolean removeFromCart(Game game) {
        return cart.remove(game);
    }

    public boolean clearCart() {
        if (cart.isEmpty())
            return false;
        cart.clear();
        return true;
    }

    public int getCartTotal() {
        return cart.stream()
                .mapToInt(Game::getPrice)
                .sum();
    }

    public boolean addGameToLibrary(Game game) {
        if (game == null || library.contains(game))
            return false;
        return library.add(game);
    }

    public boolean removeGameFromLibrary(Game game) {
        return library.remove(game);
    }
}
