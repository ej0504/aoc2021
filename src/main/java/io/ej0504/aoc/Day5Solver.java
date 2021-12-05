package io.ej0504.aoc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5Solver {

    public int part1(String fileName) {
        return process(fileName, false);
    }

    public int part2(String fileName) {
        return process(fileName, true);
    }

    private int process(String fileName, boolean includeOrthogonal) {

        Stream<Line> lines = new ResourceParser().parse(fileName)
                .stream()
                .map(this::toLine);

        Stream<Line> filtered = includeOrthogonal ? lines : lines.filter(this::isOrthogonal);

        return (int) filtered
                .flatMap(this::generatePoints)
                .collect(Collectors.toMap(p -> p, p -> 1, Integer::sum))
                .values().stream()
                .filter(value -> value > 1)
                .count();
    }

    private Point toPoint(String str) {
        String[] xy = str.split(",");
        return new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
    }

    private Line toLine(String str) {
        String[] ends = str.split(" -> ");
        return new Line(toPoint(ends[0]), toPoint(ends[1]));
    }

    private boolean isOrthogonal(Line line) {
        return line.start.x == line.end.x || line.start.y == line.end.y;
    }

    private Stream<Point> generatePoints(Line line) {
        Point dir = new Point(Integer.signum(line.end.x - line.start.x), Integer.signum(line.end.y - line.start.y));

        Stream.Builder<Point> builder = Stream.builder();
        for (Point value = line.start;
             !value.equals(line.end);
             value = new Point(value.x + dir.x, value.y + dir.y)) {
            builder.add(value);
        }
        builder.add(line.end);
        return builder.build();
    }

    private record Line(Point start, Point end) {}

    private record Point(int x, int y) {}
}
