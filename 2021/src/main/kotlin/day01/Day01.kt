package day01

import Solution

object Day01: Solution {

    override fun part1(input: List<String>): Int {
        val list = input.map { it.toInt() }
        var count = 0

        list.zipWithNext { prev, curr ->
            if (curr > prev) count++
        }

        return count
    }

    override fun part2(input: List<String>): Int {
        val list = input.map { it.toInt() }
        var count = 0

        list
            .windowed(3, 1)
            .map { it.sum() }
            .zipWithNext { prev, curr ->
                if (curr > prev) count++
            }

        return count
    }


}
fun main() {
    Day01.solve()
}