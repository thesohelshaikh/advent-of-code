package day02

import org.example.Puzzle
import kotlin.math.abs

object Day02 : Puzzle {
    private fun String.levels() = split(" ").map { it.toLong() }

    private fun List<String>.report() = map { it.levels() }

    private fun List<Long>.isIncreasing() = zipWithNext().all { (first, second) -> second > first }

    private fun List<Long>.isDecreasing() = zipWithNext().all { (first, second) -> second < first }

    private fun List<Long>.isDifferenceValid() = zipWithNext().all { (first, second) -> abs(second - first) <= 3 }

    private fun List<Long>.isSafe() = (isIncreasing() || isDecreasing()) && isDifferenceValid()

    private fun List<Long>.without(index: Int): List<Long> = filterIndexed { i, _ -> i != index }

    private fun List<Long>.isSafeWithoutOneLevel(): Boolean =
        isSafe() || indices.any { index -> without(index).isSafe() }

    override fun part1(input: List<String>): Any {
        return input.report()
            .count { it.isSafe() }
    }

    override fun part2(input: List<String>): Any {
        return input.report()
            .count { it.isSafeWithoutOneLevel() }
    }
}

fun main() {
    Day02.solve()
}