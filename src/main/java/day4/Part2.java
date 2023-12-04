package day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part2 {
    private static List<String> input = new ArrayList<>();
    private static final List<Integer> cardsAmount = new ArrayList<>();

    public static void main(String[] args) {
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(day1.Part1.class.getResourceAsStream("/day4/day4.txt")))) {
            input = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < input.size(); ++i) {
            cardsAmount.add(1);
        }

        for (int i = 0; i < input.size(); ++i) {
            String line = input.get(i);
            String trimmedLine = line.substring(getMatch(line, "Card\\s+[0-9]+:\\s").length());

            List<Integer> winningNumbers = extractNumbers(trimmedLine.split(" \\| ")[0]);
            List<Integer> cardNumbers = extractNumbers(trimmedLine.split(" \\| ")[1]);

            processCard(i, winningNumbers, cardNumbers);
        }

        System.out.println(cardsAmount.stream().mapToInt(Integer::intValue).sum());
    }

    private static String getMatch(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return "";
        }
    }

    private static List<Integer> extractNumbers(String input) {
        return Arrays.stream(input.trim().split("\\s+")).map(Integer::valueOf).collect(Collectors.toList());
    }

    private static void processCard(int currentCardIndex, List<Integer> winningNumbers, List<Integer> cardNumbers) {
        int currentCardAmount = cardsAmount.get(currentCardIndex);
        long amountOfWinningNumbers = cardNumbers.stream().filter(winningNumbers::contains).count();
        System.out.printf("Winning numbers: %s, card numbers: %s, points: %s, current %s card amount of copies: %s%n", winningNumbers, cardNumbers, amountOfWinningNumbers, currentCardIndex, cardsAmount.get(currentCardIndex));

        long lastValue = currentCardIndex + amountOfWinningNumbers > input.size() - 1
                ? input.size() - 1
                : currentCardIndex + amountOfWinningNumbers;
        for (int i = currentCardIndex + 1; i <= lastValue; ++i) {
            cardsAmount.set(i, cardsAmount.get(i) + currentCardAmount);
            System.out.printf("Added %s to the card %s, current amount there is %s.%n", currentCardAmount, i, cardsAmount.get(i));
        }
    }
}
