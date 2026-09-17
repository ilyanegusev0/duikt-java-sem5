package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Developer {
    private final List<Game> games = new ArrayList<>();

    private String name;

    @Override
    public String toString() {
        return String.format("\u001B[34m%s\u001B[0m", name);
    }

    protected void linkGame(Game game) {
        if (!games.contains(game))
            games.add(game);
    }
}
