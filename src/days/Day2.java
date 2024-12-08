package days;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 {
    public static void partOne() {
        List<String> list = Helper.readLines("day2.txt");

        long count = list.stream()
                .map(line -> line.split(" "))
                .map(l -> Stream.of(l).map(Integer::parseInt).toList())
                .filter(Day2::isSafe)
                .count();

        System.out.println(count);
    }

    private static boolean isSafe(List<Integer> list) {

        boolean good = IntStream.range(1, list.size())
                .allMatch(i -> list.get(i) > list.get(i - 1) &&
                        list.get(i) - list.get(i - 1) <= 3);

        boolean alsoGood = IntStream.range(1, list.size())
                .allMatch(i -> list.get(i) < list.get(i - 1) &&
                        list.get(i - 1) - list.get(i) <= 3);

        return good || alsoGood;
    }
}
