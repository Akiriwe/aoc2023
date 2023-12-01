package day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part2 {
    private static final String DIGITS_REGEX = "(one|two|three|four|five|six|seven|eight|nine|[0-9])";
    private static final Map<String, Integer> MAP_NUMBER = new HashMap<>();


    public static void main(String[] args) {
        List<String> input;
        int sum = 0;
        MAP_NUMBER.put("one", 1);
        MAP_NUMBER.put("two", 2);
        MAP_NUMBER.put("three", 3);
        MAP_NUMBER.put("four", 4);
        MAP_NUMBER.put("five", 5);
        MAP_NUMBER.put("six", 6);
        MAP_NUMBER.put("seven", 7);
        MAP_NUMBER.put("eight", 8);
        MAP_NUMBER.put("nine", 9);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Part2.class.getResourceAsStream("/day1/day1.txt")))) {
            input = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String line : input) {
            int digitOne = findDigit(line, true);
            int digitTwo = findDigit(line, false);
            String number = String.valueOf(digitOne) + digitTwo;
            System.out.printf("Line - %s, digit one - %s, digit two - %s, result - %s%n", line, digitOne, digitTwo, number);

            sum += Integer.parseInt(number);
        }

        System.out.println(sum);
    }

    private static int findDigit(String input, boolean first) {
        String regex = first ? DIGITS_REGEX + ".*" : ".*" + DIGITS_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String result = matcher.group(1);

            if (result.length() == 1 & Character.isDigit(result.charAt(0))) {
                return Integer.parseInt(result);
            } else {
                return MAP_NUMBER.get(result);
            }
        } else {
            return -1;
        }
    }
}
