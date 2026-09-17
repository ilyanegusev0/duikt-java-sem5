package models;

import enums.Genre;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.WordUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Game {
    private static int idCounter = 1;

    private final int id = idCounter++;

    private String title;
    private String description;
    private LocalDate releaseDate;
    private List<Genre> genres;
    private List<Developer> developers;
    private int price;
    private boolean isAvailable = true;

    public Game(String title, String description, LocalDate releaseDate, List<Genre> genres, List<Developer> developers, int price) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.developers = developers;
        this.price = price;

        if (developers != null) {
            for (Developer dev : developers)
                dev.linkGame(this);
        }
    }

    @Override
    public String toString() {
        String genresString = String.join(", ", genres.stream().map(Enum::name).toList());

        String developersString = String.join(", ", developers.stream().map(Developer::toString).toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM, yyyy");
        String formattedDate = releaseDate.format(formatter);

        String addToCart = isAvailable
                ? String.format("\u001B[1m\u001B[32m[ %d₴ | Add to Cart ]\u001B[0m", price)
                : String.format("\u001B[31m%s is no longer available on the store.\u001B[0m", title);

        return String.format("""
                        \u001B[1m%s [%d]\u001B[0m
                        
                        %s
                        
                        \u001B[1mGENRE:\u001B[0m %s
                        \u001B[1mDEVELOPER:\u001B[0m %s
                        \u001B[1mRELEASE DATE:\u001B[0m %s
                        
                        %s""",
                title, id, WordUtils.wrap(description, 100), genresString, developersString, formattedDate, addToCart);
    }
}
