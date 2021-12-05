package io.ej0504.aoc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day5SolverTest {

    private final Day5Solver underTest = new Day5Solver();

    @Test
    void part1_example() {
        assertThat(underTest.part1("day5_example.txt")).isEqualTo(5);
    }

    @Test
    void part1_input() {
        assertThat(underTest.part1("day5_input.txt")).isEqualTo(5690);
    }

    @Test
    void part2_example() {
        assertThat(underTest.part2("day5_example.txt")).isEqualTo(12);
    }

    @Test
    void part2_input() {
        assertThat(underTest.part2("day5_input.txt")).isEqualTo(17741);
    }

}