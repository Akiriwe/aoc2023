package day4;

import util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) {
        List<String> input = Util.getLines("/day1/day1.txt");
        int sum = 0;

        for (String line : input) {
            String trimmedLine = line.substring(Util.getMatch(line, "Card\\s+[0-9]+:\\s").length());

            List<Integer> winningNumbers = extractNumbers(trimmedLine.split(" \\| ")[0]);
            List<Integer> cardNumbers = extractNumbers(trimmedLine.split(" \\| ")[1]);

            int pointsForTheCard = getWinningNumbersMultiplication(winningNumbers, cardNumbers);
            sum += pointsForTheCard;

            System.out.printf("Winning numbers: %s, card numbers: %s, points: %s, intermediary sum: %s%n", winningNumbers, cardNumbers, pointsForTheCard, sum);
        }
    }

    private static List<Integer> extractNumbers(String input) {
        return Arrays.stream(input.trim().split("\\s+")).map(Integer::valueOf).collect(Collectors.toList());
    }

    private static int getWinningNumbersMultiplication(List<Integer> winningNumbers, List<Integer> cardNumbers) {
        long amountOfWinningNumbers = cardNumbers.stream().filter(winningNumbers::contains).count();
        int points = 0;

        for (int i = 1; i <= amountOfWinningNumbers; i++) {
            if (i == 1) {
                points = i;
            } else {
                points *= 2;
            }
        }

        return points;
    }
}
