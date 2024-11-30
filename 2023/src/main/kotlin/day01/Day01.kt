package day01

import Puzzle

object Day01 : Puzzle {
    override fun part1(input: List<String>): Any {
        var sum = 0
        input.forEach { line ->
            val firstDigit = line.firstOrNull { it.isDigit() }
            val lastDigit = line.lastOrNull { it.isDigit() }
            val number = ("$firstDigit$lastDigit").toIntOrNull()
            if (number != null) {
                sum += number
            }
        }
        return sum
    }

    override fun part2(input: List<String>): Any {
        val stringDigits = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
            "1" to 1,
            "2" to 2,
            "3" to 3,
            "4" to 4,
            "5" to 5,
            "6" to 6,
            "7" to 7,
            "8" to 8,
            "9" to 9,
        )

        var sum = 0
        input.forEach { line ->
            val firstDigit = stringDigits[line.findAnyOf(stringDigits.keys)?.second]
            val lastDigit = stringDigits[line.findLastAnyOf(stringDigits.keys)?.second]
            val number = ("$firstDigit$lastDigit").toIntOrNull()
            if (number != null) sum += number

        }
        return sum
    }

}

fun main() {
    Day01.solve()
}