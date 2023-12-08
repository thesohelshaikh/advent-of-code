package day08

import Puzzle

object Day08: Puzzle {
    override fun part1(input: List<String>): Any {
        val instructions = input.first()
        val map = input.drop(2).associate {
            val nodeStr = it.substringBefore(" = ")
            val first = getNodeElement(it)[0]
            val second = getNodeElement(it)[1]
            nodeStr to Pair(first, second)
        }


        var toMatch = map.keys.first()
        var count = 0
        var index = 0
        do {
            val matched = map[toMatch]!!
            val inst = instructions[index]
            toMatch = if (inst == 'L') { matched.first } else { matched.second }
            count++
            index++

            if (index >= instructions.length) index = 0
        } while (toMatch != "ZZZ")

        return count
    }

    private fun getNodeElement(it: String) = it.substringAfter(" (").dropLast(1).split(", ")

    override fun part2(input: List<String>): Any {
        return 0
    }

}

fun main() {
    Day08.solve()
}