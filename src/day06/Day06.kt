package day06

import Puzzle

object Day06: Puzzle {

    private fun calculateWaysToBeatRecords(times: List<Long>, distances: List<Long>): Long {
        return times.zip(distances) { time, distance ->
            (1 until time).count { speed -> speed * (time - speed) > distance }.toLong()
        }.filter { it > 0 }.fold(1L) { acc, num -> acc * num }
    }

    override fun part1(input: List<String>): Any {
        val times = input[0].substringAfter(":").split(" ").filterNot { it.isEmpty() }.map { it.trim().toLong() }
        val distances = input[1].substringAfter(":").split(" ").filterNot { it.isEmpty() }.map { it.trim().toLong() }
        return calculateWaysToBeatRecords(times, distances)
    }

    override fun part2(input: List<String>): Any {
        val time = input[0].substringAfter(":").filter { it.isDigit() }.trim().toLong()
        val distance = input[1].substringAfter(":").filter { it.isDigit() }.trim().toLong()
        return calculateWaysToBeatRecords(listOf(time), listOf(distance))
    }
}

fun main() {
    Day06.solve()
}