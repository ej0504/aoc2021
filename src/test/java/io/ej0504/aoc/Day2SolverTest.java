package io.ej0504.aoc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day2SolverTest {

    private final Day2Solver underTest = new Day2Solver();

    @Test
    void part1_example() {
        assertThat(underTest.part1("day2_example.txt")).isEqualTo(150);
    }

    @Test
    void part1_input() {
        assertThat(underTest.part1("day2_input.txt")).isEqualTo(2117664);
    }

    @Test
    void part2_example() {
        assertThat(underTest.part2("day2_example.txt")).isEqualTo(900);
    }

    @Test
    void part2_input() {
        assertThat(underTest.part2("day2_input.txt")).isEqualTo(2117664);
    }
}