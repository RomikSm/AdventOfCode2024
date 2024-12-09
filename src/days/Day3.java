package days;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void partOne() {
        List<String> list = Helper.readLines("day3.txt");
        int result = sumOfMuls(list);
        System.out.println(result);
    }

    public static void partTwo() {
        List<String> list = Helper.readLines("day3.txt");
        int result = sumOfMulsEnhanced(list);
        System.out.println(result);
    }

    private static int sumOfMuls(List<String> list) {
        Pattern mulPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        return list.stream()
                .flatMap(line -> {
                    Matcher matcher = mulPattern.matcher(line);
                    return matcher.results()
                            .map(matchResult -> {
                                int x = Integer.parseInt(matchResult.group(1));
                                int y = Integer.parseInt(matchResult.group(2));
                                return x * y;
                            });
                })
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static int sumOfMulsEnhanced(List<String> list) {
        Pattern pattern = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\))");

        boolean[] enabled = {true};

        return list.stream()
                .flatMap(line ->
                        pattern.matcher(line).results()
                                .map(MatchResult::group)
                )
                .mapToInt(action -> {
                    if (action.equals("do()")) {
                        enabled[0] = true;
                        return 0;
                    } else if (action.equals("don't()")) {
                        enabled[0] = false;
                        return 0;
                    } else if (action.startsWith("mul(") && enabled[0]) {
                        String[] parts = action.substring(4, action.length() - 1).split(",");
                        return Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
                    }
                    return 0;
                })
                .sum();
    }
}
