package day03

import org.example.Puzzle

object Day03 : Puzzle {

    private fun multiplyNumbers(instructions: String): Long {
        val mulPattern = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        return mulPattern.findAll(instructions).sumOf { matchResult ->
            val (a, b) = matchResult.destructured
            a.toLong() * b.toLong()
        }
    }

    override fun part1(input: List<String>): Any {
        val program = input.joinToString("")

        return multiplyNumbers(program)
    }

    override fun part2(input: List<String>): Any {
        val program = input.joinToString("")

        val enabled = program.replace("""don't\(\).*?do\(\)""".toRegex(), "")

        return multiplyNumbers(enabled)
    }
}

fun main() {
    Day03.solve()
}