package models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class Order {
    private static int idCounter = 1;

    private int id = idCounter++;
    private LocalDateTime createdAt = LocalDateTime.now();

    private final User user;
    private final List<Game> games;
    private final int totalPrice;

    @Override
    public String toString() {
        String gameTitles = " - " + String.join("\n - ", games.stream().map(Game::getTitle).toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM, yyyy HH:mm:ss");
        String formattedDate = createdAt.format(formatter);

        return String.format("""
                        \u001B[1mORDER #%d\u001B[0m
                        
                        \u001B[1mUSER:\u001B[0m \u001B[34m%s\u001B[0m
                        \u001B[1mTOTAL:\u001B[0m %d₴
                        \u001B[1mDATE:\u001B[0m %s
                        
                        \u001B[1mGAMES (%d items):\u001B[0m
                        %s""",
                id, user.getName(), totalPrice, formattedDate, games.size(), gameTitles);
    }
}
