package day02

import Solution

object Day02 : Solution {

    enum class Direction {
        FORWARD, DOWN, UP
    }

    data class Instruction(val direction: Direction, val steps: Int)

    private fun parseInstructions(input: List<String>): ArrayList<Instruction> {
        val instructions = ArrayList<Instruction>()

        input.forEach { command ->
            val (directionStr, steps) = command.split(" ")

            val direction = when (directionStr) {
                "forward" -> Direction.FORWARD
                "down" -> Direction.DOWN
                else -> Direction.UP
            }
            instructions.add(Instruction(direction, steps.toInt()))
        }
        return instructions
    }

    private fun getSubmarinePosition(instructions: ArrayList<Instruction>): Int {
        var x = 0
        var y = 0

        instructions.forEach { instruction ->
            when (instruction.direction) {
                Direction.FORWARD -> x += instruction.steps
                Direction.UP -> y -= instruction.steps
                Direction.DOWN -> y += instruction.steps
            }
        }
        return x * y
    }

    private fun getSubmarinePositionWithAim(instructions: ArrayList<Instruction>): Int {
        var x = 0
        var y = 0
        var aim = 0

        instructions.forEach { instruction ->
            when (instruction.direction) {
                Direction.FORWARD -> {
                    x += instruction.steps
                    if (aim != 0) {
                        y += instruction.steps * aim
                    }
                }
                Direction.UP -> aim -= instruction.steps
                Direction.DOWN -> aim += instruction.steps
            }
        }

        return x * y
    }

    override fun part1(input: List<String>): Any {
        val instructions = parseInstructions(input)

        return getSubmarinePosition(instructions)

    }

    override fun part2(input: List<String>): Any {
        val instructions = parseInstructions(input)

        return getSubmarinePositionWithAim(instructions)
    }
}

fun main() {
    Day02.solve()
}