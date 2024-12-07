package day07

import kotlinx.coroutines.*
import org.example.Puzzle

object Day07 : Puzzle {

    interface Operator {
        operator fun invoke(a: Long, b: Long): Long
    }

    object Add : Operator {
        override fun invoke(a: Long, b: Long): Long = a + b
    }

    object Multiply : Operator {
        override fun invoke(a: Long, b: Long): Long = a * b
    }

    object Concat : Operator {
        override fun invoke(a: Long, b: Long): Long = "$a$b".toLong()
    }

    private fun generateOperatorCombinations(
        size: Int,
        operators: List<Operator>
    ): List<List<Operator>> {
        val combinations = mutableListOf<List<Operator>>()
        val current = MutableList(size) { operators.first() }

        while (true) {
            combinations.add(current.toList())

            var index = size - 1
            while (index >= 0 && current[index] == operators.last()) {
                current[index] = operators.first()
                index--
            }

            if (index < 0) break

            current[index] = operators[operators.indexOf(current[index]) + 1]
        }

        return combinations
    }

    private fun evaluateExpression(
        numbers: List<Long>,
        operators: List<Operator>
    ): Long {
        return operators.foldIndexed(numbers.first()) { index, acc, operator ->
            operator(acc, numbers[index + 1])
        }
    }

    private fun isValidEquation(
        target: Long,
        numbers: List<Long>,
        operators: List<Operator>
    ): Boolean {
        if (numbers.size == 1) return numbers.first() == target

        val numOperators = numbers.size - 1
        val operatorCombinations = generateOperatorCombinations(numOperators, operators)

        operatorCombinations.forEach { operatorCombo ->
            if (evaluateExpression(numbers, operatorCombo) == target) return true
        }

        return false
    }

    private fun parse(input: List<String>) = input.map {
        val (ans, testValue) = it.split(":")
        val parsedTestValues = testValue.split(" ")
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
        ans.toLong() to parsedTestValues
    }

    override fun part1(input: List<String>): Any {
        val parsed = parse(input)

        return parsed.sumOf { (target, numbers) ->
            if (isValidEquation(target, numbers, listOf(Add, Multiply))) target else 0
        }
    }

    override fun part2(input: List<String>): Any = runBlocking {
        val parsed = parse(input)

        val result = parsed
            .map { (target, numbers) ->
                async(Dispatchers.Default) {
                    if (isValidEquation(target, numbers, listOf(Add, Multiply, Concat))) target else 0
                }
            }
            .awaitAll()

        return@runBlocking result.sum()
    }
}

fun main() {
    Day07.solve()
}