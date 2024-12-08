package days;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day1 {
    public static void partOne() {
        
        List<String> list = Helper.readLines("day1.txt");

        Queue<Integer> listA = list.stream()
                .map(a -> a.trim().split("\\s+")[0])
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(PriorityQueue::new));

        Queue<Integer> listB = list.stream()
                .map(a -> a.trim().split("\\s+")[1])
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(PriorityQueue::new));

        int sum = 0;

        while (!listA.isEmpty()) {
            sum += Math.abs(listA.poll() - listB.poll());
        }

        System.out.println(sum);
    }

    public static void partTwo() {
        List<String> list = Helper.readLines("day1.txt");

        List<Integer> listA = list.stream()
                .map(a -> a.trim().split("\\s+")[0])
                .map(Integer::parseInt)
                .toList();

        List<Integer> listB = list.stream()
                .map(a -> a.trim().split("\\s+")[1])
                .map(Integer::parseInt)
                .toList();

        int sum = 0;

        for (Integer integer : listA) {
            sum += Collections.frequency(listB, integer) * integer;
        }

        System.out.println(sum);
    }
}
