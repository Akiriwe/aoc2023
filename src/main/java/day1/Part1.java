package day1;

import util.Util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        List<String> input = Util.getLines("/day1/day1.txt");
        int sum = 0;

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
