package io.ej0504.aoc;

import java.util.List;

public class Day2Solver {

    public int part1(String fileName) {
        ResourceParser resourceParser = new ResourceParser();
        List<String> lines = resourceParser.parse(fileName);
        return calculateDepthMultipliedByHorizontal(lines);
    }

    public int part2(String fileName) {
        ResourceParser resourceParser = new ResourceParser();
        List<String> lines = resourceParser.parse(fileName);
        return calculatePart2(lines);
    }

    private int calculateDepthMultipliedByHorizontal(List<String> lines) {
        int depth = 0;
        int horizontal = 0;

        for (String line : lines) {
            String[] parts = line.split(" ");
            String command = parts[0];
            int amount = Integer.parseInt(parts[1]);

            switch (command) {
                case "forward":
                    horizontal += amount;
                    break;
                case "down":
                    depth += amount;
                    break;
                case "up":
                    depth -= amount;
                    break;
            }
        }
        return depth * horizontal;
    }

    private int calculatePart2(List<String> lines) {
        int depth = 0;
        int horizontal = 0;
        int aim = 0;

        for (String line : lines) {
            String[] parts = line.split(" ");
            String command = parts[0];
            int amount = Integer.parseInt(parts[1]);

            switch (command) {
                case "forward":
                    horizontal += amount;
                    depth += aim * amount;
                    break;
                case "down":
                    aim += amount;
                    break;
                case "up":
                    aim -= amount;
                    break;
            }
        }
        return depth * horizontal;
    }
}
