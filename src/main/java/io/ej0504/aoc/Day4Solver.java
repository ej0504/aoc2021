package io.ej0504.aoc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4Solver {

    public int part1(String fileName) {
        ResourceParser resourceParser = new ResourceParser();
        List<String> lines = resourceParser.parse(fileName);

        List<Integer> inputs = stringToInts(lines.get(0), ",");
        List<Board> boards = createBoards(lines);
        return findWinner(inputs, boards);
    }

    private List<Board> createBoards(List<String> lines) {
        List<Board> boards = new ArrayList<>();

        for (int i = 1; i < lines.size() - 5; i += 6) {
            if (lines.get(i).isEmpty()) {
                boards.add(createBoard(lines, i));
            }
        }
        return boards;
    }

    public int part2(String fileName) {
        ResourceParser resourceParser = new ResourceParser();
        List<String> lines = resourceParser.parse(fileName);

        List<Integer> inputs = stringToInts(lines.get(0), ",");
        List<Board> boards = createBoards(lines);
        return findLastWinner(inputs, boards);
    }

    private int findWinner(List<Integer> inputs, List<Board> boards) {
        for(int input : inputs) {
            for(Board board : boards) {
                if(board.lookUp.containsKey(input)) {
                    Pair pair = board.lookUp.get(input);
                    Cell cell = board.grid[pair.x][pair.y];
                    cell.marked = true;

                    if(isBingoX(board.grid, pair) || isBingoY(board.grid, pair)) {
                        return calculateResult(board, input);
                    }
                }
            }
        }
        throw new IllegalStateException("oh bother");
    }

    private int findLastWinner(List<Integer> inputs, List<Board> boards) {
        Set<Board> winners = new HashSet<>(boards);
        for(int input : inputs) {
            for(Board board : boards) {

                if(winners.contains(board) && board.lookUp.containsKey(input)) {
                    Pair pair = board.lookUp.get(input);
                    Cell cell = board.grid[pair.x][pair.y];
                    cell.marked = true;

                    if(isBingoX(board.grid, pair) || isBingoY(board.grid, pair)) {
                        winners.remove(board);

                        if(winners.isEmpty()) {
                            return calculateResult(board, input);
                        }
                    }
                }
            }
        }
        throw new IllegalStateException("oh bother");
    }

    private int calculateResult(Board winner, int winningInput) {
        return Arrays.stream(winner.grid).flatMap(Arrays::stream)
                .filter(cell -> !cell.marked)
                .map(cell -> cell.value).reduce(0, Integer::sum) * winningInput;
    }

    private boolean isBingoX(Cell[][] grid, Pair pair) {
        return Arrays.stream(grid[pair.x]).allMatch(c -> c.marked);
    }

    private boolean isBingoY(Cell[][] grid, Pair pair) {
        return IntStream.range(0, grid.length).allMatch(x -> grid[x][pair.y].marked);
    }

    private Board createBoard(List<String> lines, int i) {
        Cell[][] board = new Cell[5][5];
        Map<Integer, Pair> lookUp = new HashMap<>();
        for (int x = 0; x < 5; x++) {
            List<Cell> row = stringToInts(lines.get(i + 1 + x), " ")
                    .stream().map(Cell::new).toList();
            board[x] = row.toArray(new Cell[5]);

            updateLookUp(lookUp, x, row);
        }
        return new Board(board, lookUp);
    }

    private void updateLookUp(Map<Integer, Pair> lookUp, int x, List<Cell> row) {
        for(int y = 0; y < row.size(); y++) {
            lookUp.put(row.get(y).value, new Pair(x, y));
        }
    }

    private List<Integer> stringToInts(String str, String delimiter) {
        return Arrays.stream(str.split(delimiter))
                .filter(s -> !s.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
    }

    private record Board(Cell[][] grid, Map<Integer, Pair> lookUp){}

    private record Pair(int x, int y) {}

    private static class Cell {
        private final int value;
        private boolean marked = false;

        private Cell(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format(marked ? "[%d]" : "_%d_", value);
        }
    }
}
