package io.ej0504.aoc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day3SolverTest {

    private final Day3Solver underTest = new Day3Solver();

    @Test
    void part1_example() {
        assertThat(underTest.part1("day3_example.txt")).isEqualTo(198);
    }

    @Test
    void part1_input() {
        assertThat(underTest.part1("day3_input.txt")).isEqualTo(3374136);
    }

    @Test
    void part2_example() {
        assertThat(underTest.part2("day3_example.txt")).isEqualTo(230);
    }

    @Test
    void part2_input() {
        assertThat(underTest.part2("day3_input.txt")).isEqualTo(4432698);
    }

}