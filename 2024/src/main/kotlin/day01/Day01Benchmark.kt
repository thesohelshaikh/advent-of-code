package day01

import kotlinx.benchmark.*
import org.example.readInput
import org.openjdk.jmh.annotations.Fork
import java.util.*

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 0)
class Day01Benchmark {

    private final var input = emptyList<String>()

    @Setup
    fun setup() {
        input = readInput("src/main/kotlin/day01/Day01")
    }

    @Benchmark
    fun part1(): Any {
        return Day01.part1(input)
    }

    @Benchmark
    fun part2(): Any {
        return Day01.part2(input)
    }
}