package io.ej0504.aoc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day4SolverTest {

    private final Day4Solver underTest = new Day4Solver();

    @Test
    void part1_example() {
        assertThat(underTest.part1("day4_example.txt")).isEqualTo(4512);
    }

    @Test
    void part1_input() {
        assertThat(underTest.part1("day4_input.txt")).isEqualTo(63424);
    }

    @Test
    void part2_example() {
        assertThat(underTest.part2("day4_example.txt")).isEqualTo(1924);
    }

    @Test
    void part2_input() {
        assertThat(underTest.part2("day4_input.txt")).isEqualTo(23541);
    }

}