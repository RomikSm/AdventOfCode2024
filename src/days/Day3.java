package days;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void partOne() {
        List<String> list = Helper.readLines("day3.txt");
        int result = sumOfMuls(list);
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
}
