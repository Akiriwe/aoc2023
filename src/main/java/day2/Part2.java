package day2;

import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    private static final String RED = "red";
    private static final String GREEN = "green";
    private static final String BLUE = "blue";

    private static final List<Game> GAMES = new ArrayList<>();

    public static void main(String[] args) {
        List<String> input = Util.getLines("/day2/day2.txt");
        AtomicInteger sum = new AtomicInteger();

        for (String line : input) {
            Game game = new Game();
            game.setId(getGameId(line));

            String trimmedLine = line.substring(Util.getMatch(line, "Game\\s+[0-9]+:\\s").length()).trim();
            String[] unprocessedSubsets = trimmedLine.split("; ");
            game.setSubsets(getSubsets(unprocessedSubsets));

            GAMES.add(game);
            System.out.println(game);
        }

        GAMES.forEach(game -> {
            Subset subset = game.getTheMinimalSubsetToRunTheGame();
            System.out.printf("Minimal subset for game %s: %s%n", game.getId(), subset);
            sum.addAndGet(subset.getBlue() * subset.getGreen() * subset.getRed());
        });

        System.out.println(sum);
    }

    private static int getGameId(String line) {
        String regex = "Game ([0-9]+):";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            return -1;
        }
    }

    private static List<Subset> getSubsets(String[] unprocessedSubsets) {
        List<Subset> subsets = new ArrayList<>();
        for (String unprocessedSubset : unprocessedSubsets) {
            Subset subset = new Subset();

            subset.setRed(getCubesAmount(unprocessedSubset, RED));
            subset.setBlue(getCubesAmount(unprocessedSubset, BLUE));
            subset.setGreen(getCubesAmount(unprocessedSubset, GREEN));

            subsets.add(subset);
        }

        return subsets;
    }

    private static int getCubesAmount(String unprocessedSubset, String color) {
        String regex = "([0-9]+)\\s" + color;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(unprocessedSubset);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        return 0;
    }
}
