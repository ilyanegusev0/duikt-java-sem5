import enums.Genre;
import models.Developer;
import models.Game;
import models.GameStore;
import models.User;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main() {
        GameStore store = new GameStore();


        // DEVELOPERS

        Developer rockstarNorth = new Developer("Rockstar North");
        Developer fromSoftware = new Developer("FromSoftware, Inc.");
        Developer qloc = new Developer("QLOC");
        Developer cdProjectRed = new Developer("CD PROJEKT RED");
        Developer ghostGames = new Developer("Ghost Games");
        Developer ubisoftMontreal = new Developer("Ubisoft Montreal");
        Developer ubisoftMontrealMassiveEntertainmentAndUbisoftShanghai = new Developer("Ubisoft Montreal, Massive Entertainment, and Ubisoft Shanghai");


        // GAMES

        Game darkSouls3 = new Game("DARK SOULS™ III",
                "Dark Souls continues to push the boundaries with the latest, ambitious chapter in the critically-acclaimed and genre-defining series. Prepare yourself and Embrace The Darkness!",
                LocalDate.of(2016, 4, 11),
                List.of(Genre.Action),
                List.of(qloc, fromSoftware),
                990);
        store.addGame(darkSouls3);

        Game gtaVLegacy = new Game("Grand Theft Auto V Legacy",
                "Grand Theft Auto V for PC offers players the option to explore the award-winning world of Los Santos and Blaine County in resolutions of up to 4k and beyond, as well as the chance to experience the game running at 60 frames per second.",
                LocalDate.of(2015, 4, 14),
                List.of(Genre.Action, Genre.Adventure),
                List.of(rockstarNorth),
                867);
        gtaVLegacy.setAvailable(false);
        store.addGame(gtaVLegacy);

        Game theWitcher3 = new Game("The Witcher 3: Wild Hunt - Complete Edition",
                "You are Geralt of Rivia, mercenary monster slayer. Before you stands a war-torn, monster-infested continent you can explore at will. Your current contract? Tracking down Ciri — the Child of Prophecy, a living weapon that can alter the shape of the world.",
                LocalDate.of(2015, 5, 18),
                List.of(Genre.RPG),
                List.of(cdProjectRed),
                1349);
        store.addGame(theWitcher3);

        Game needForSpeedPayback = new Game("Need for Speed™ Payback",
                "Set in the underworld of Fortune Valley, you and your crew were divided by betrayal and reunited by revenge to take down The House, a nefarious cartel that rules the city’s casinos, criminals and cops. In this corrupt gambler’s paradise, the stakes are high and The House always wins.",
                LocalDate.of(2017, 11, 6),
                List.of(Genre.Action, Genre.Adventure, Genre.Racing, Genre.Sports, Genre.Strategy),
                List.of(ghostGames),
                799);
        store.addGame(needForSpeedPayback);

        Game assassinsCreedRevelations = new Game("Assassin's Creed® Revelations",
                "Ezio Auditore walks in the footsteps of the legendary mentor Altair, on a dangerous journey of discovery and revelation.",
                LocalDate.of(2011, 12, 1),
                List.of(Genre.Action, Genre.Adventure),
                List.of(ubisoftMontreal),
                225);
        store.addGame(assassinsCreedRevelations);

        Game farCry3 = new Game("Far Cry 3",
                "Stranded on the Rook Islands as Jason Brody, fight your way through a lawless tropical open world using a powerful arsenal to save your friends and escape the madness of this acclaimed visceral FPS.",
                LocalDate.of(2012, 11, 29),
                List.of(Genre.Action, Genre.Adventure),
                List.of(ubisoftMontrealMassiveEntertainmentAndUbisoftShanghai),
                299);
        store.addGame(farCry3);


        // USERS

        User relax = new User("Релакс",
                "Man, this party stinks. I f*cking hate these people.",
                24);
        store.addUser(relax);

        User miraDiv = new User("MiraDiv",
                "Made in Ukraine",
                25);
        store.addUser(miraDiv);


        // CHECKOUTS

        relax.addFunds(5000);
        miraDiv.addFunds(10000);

        relax.addToCart(theWitcher3);
        relax.addToCart(darkSouls3);
        store.checkout(relax);

        miraDiv.addToCart(gtaVLegacy);
        miraDiv.addToCart(needForSpeedPayback);
        miraDiv.addToCart(farCry3);
        store.checkout(miraDiv);

        relax.addToCart(assassinsCreedRevelations);
        store.checkout(relax);


        // DEMONSTRATIONS

        store.showGames();
        store.showUsers();
        store.showOrders();
    }
}
