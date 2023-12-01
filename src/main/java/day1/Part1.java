package day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) {
        List<String> input;
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Part1.class.getResourceAsStream("/day1/day1.txt")))) {
            input = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String line : input) {
            String number = String.copyValueOf(new char[] {line.charAt(findDigitIndex(line, true)), line.charAt(findDigitIndex(line, false))});
            System.out.println(number);

            sum += Integer.parseInt(number);
        }

        System.out.println(sum);
    }

    private static int findDigitIndex(String input, boolean first) {
        String regex = first ? "([0-9]).*" : ".*([0-9])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.start(1);
        } else {
            // Digit not found
            return -1;
        }
    }
}
