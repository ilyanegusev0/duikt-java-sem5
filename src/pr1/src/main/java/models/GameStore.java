package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameStore {
    private List<Game> games = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();


    public boolean addGame(Game game) {
        if (games.contains(game))
            return false;

        return games.add(game);
    }

    public boolean removeGame(Game game) {
        if (!games.contains(game))
            return false;

        return games.remove(game);
    }

    public boolean addUser(User user) {
        if (users.contains(user))
            return false;

        return users.add(user);
    }

    public boolean removeUser(User user) {
        if (!users.contains(user))
            return false;

        return users.remove(user);
    }

    public boolean addOrder(Order order) {
        if (orders.contains(order))
            return false;
        return orders.add(order);
    }

    public boolean removeOrder(Order order) {
        if (!orders.contains(order))
            return false;
        return orders.remove(order);
    }

    public Order checkout(User user) {
        if (user == null || !users.contains(user))
            return null;

        List<Game> cart = user.getCart();
        if (cart.isEmpty())
            return null;

        int cartTotal = user.getCartTotal();
        if (user.getBalance() < cartTotal)
            return null;

        List<Game> purchased = List.copyOf(cart);

        user.withdrawFunds(cartTotal);
        purchased.forEach(user::addGameToLibrary);
        user.clearCart();

        Order order = new Order(user, purchased, cartTotal);
        addOrder(order);
        return order;
    }

    public void showGames() {
        System.out.println(String.format("\u001B[1m\nGAMES (%d items)\u001B[0m", games.size()));
        System.out.println("-".repeat(200));

        for (Game game : games) {
            System.out.println(game);
            System.out.println("-".repeat(200));
        }
    }

    public void showUsers() {
        System.out.println(String.format("\u001B[1m\nUSERS (%d items)\u001B[0m", users.size()));
        System.out.println("-".repeat(200));

        for (User user : users) {
            System.out.println(user);
            System.out.println("-".repeat(200));
        }
    }

    public void showOrders() {
        System.out.println(String.format("\u001B[1m\nORDERS (%d items)\u001B[0m", orders.size()));
        System.out.println("-".repeat(200));

        for (Order order : orders) {
            System.out.println(order);
            System.out.println("-".repeat(200));
        }
    }
}
