package io.ej0504.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Day3Solver {

    public int part1(String fileName) {
        ResourceParser resourceParser = new ResourceParser();
        List<String> lines = resourceParser.parse(fileName);

        List<Integer> totalBits = totalBits(lines);

        List<Integer> gamma = totalBits.stream().map(i -> i > lines.size() / 2 ? 1 : 0).collect(Collectors.toList());
        List<Integer> epsilon = gamma.stream().map(i -> i > 0 ? 0 : 1).collect(Collectors.toList());

        return toDecimal(gamma) * toDecimal(epsilon);
    }

    public int part2(String fileName) {
        ResourceParser resourceParser = new ResourceParser();
        List<String> lines = resourceParser.parse(fileName);

        List<Integer> oxygen = bitCriteriaFilter(lines, this::mostCommonMap);
        List<Integer> co2 = bitCriteriaFilter(lines, this::leastCommonMap);

        return toDecimal(oxygen) * toDecimal(co2);
    }

    private List<Integer> bitCriteriaFilter(List<String> lines, BiFunction<Integer, Integer, Integer> commonMap) {
        List<List<Integer>> remaining = lines.stream().map(this::toIntList).collect(Collectors.toList());

        for (int i = 0; i < lines.get(0).length(); i++) {
            int finalI = i;

            List<Integer> bitCriteria = calculateBitCriteria(remaining, commonMap);
            remaining = remaining.stream()
                    .filter(n -> Objects.equals(n.get(finalI), bitCriteria.get(finalI)))
                    .collect(Collectors.toList());

            if (remaining.size() == 1) {
                return remaining.get(0);
            }
        }
        throw new IllegalStateException("oh bother");
    }

    private List<Integer> calculateBitCriteria(List<List<Integer>> lines, BiFunction<Integer, Integer, Integer> commonMap) {
        List<Integer> startList = filledList(lines.get(0).size());

        int middle = calcMiddle(lines.size());
        return  lines.stream()
                .reduce(startList, this::zipSum)
                .stream()
                .map(i -> commonMap.apply(middle, i))
                .collect(Collectors.toList());
    }

    private List<Integer> filledList(int size) {
        Integer[] start = new Integer[size];
        int value = 0;
        Arrays.fill(start, value);
        return Arrays.asList(start);
    }

    private int mostCommonMap(int middle, Integer i) {
        return i >= middle ? 1 : 0;
    }

    private int leastCommonMap(int middle, Integer i) {
        return i >= middle ? 0 : 1;
    }

    private List<Integer> totalBits(List<String> lines) {
        List<Integer> startList = filledList(lines.get(0).length());

        return lines.stream().map(this::toIntList)
                .reduce(startList, this::zipSum);
    }

    private int calcMiddle(int n) {
        return (int) Math.ceil(n / 2.0);
    }

    private List<Integer> zipSum(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>(a.size());
        for (int i = 0; i < a.size(); i++) {
            result.add(a.get(i) + b.get(i));
        }
        return result;
    }

    private int toDecimal(List<Integer> list) {
        // convert back to a String, then parse into Binary int
        return Integer.parseInt(list.stream().map(x -> x + "").reduce("", (a, b) -> a + b), 2);
    }

    private List<Integer> toIntList(String str) {
        return Arrays.stream(str.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
