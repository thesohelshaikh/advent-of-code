package day01

import org.example.Puzzle
import kotlin.math.abs

object Day01 : Puzzle {
    private fun parseList(input: List<String>) = input.map { line ->
        val (first, second) = line
            .split(" ")
            .filter { it.isNotEmpty() }
            .map { it.trim().toLong() }
        first to second
    }

    override fun part1(input: List<String>): Any {
        val pairs = parseList(input)

        val left = pairs.map { it.first }.sorted()
        val right = pairs.map { it.second }.sorted()

        return left.zip(right) { l, r -> abs(l - r) }.sum()
    }

    override fun part2(input: List<String>): Any {
        val pairs = parseList(input)

        val freqMap = pairs
            .map { it.second }
            .groupingBy { it }
            .eachCount()

        return pairs.sumOf { (first, _) ->
            first * freqMap.getOrDefault(first, 0)
        }
    }
}

fun main() {
    Day01.solve()
}