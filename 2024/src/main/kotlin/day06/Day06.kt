package day06

import org.example.Puzzle

object Day06 : Puzzle {
    data class Position(val x: Int, val y: Int)

    private enum class Direction(val dx: Int, val dy: Int) {
        NORTH(0, -1),
        EAST(1, 0),
        SOUTH(0, 1),
        WEST(-1, 0);

        fun move(position: Position) = Position(position.x + dx, position.y + dy)

        fun turn(): Direction = entries[(ordinal + 1) % entries.size]
    }

    private fun List<String>.getChar(position: Position) = getOrNull(position.y)?.getOrNull(position.x)

    override fun part1(input: List<String>): Any {
        var initialPosition = Position(-1, -1)
        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, char ->
                if (char == '^') initialPosition = Position(x, y)
            }
        }

        var direction = Direction.NORTH
        var currentPosition = initialPosition
        val totalVisited = mutableSetOf(initialPosition)

        while (true) {
            val nextPosition = direction.move(currentPosition)

            when (input.getChar(nextPosition)) {
                '.', '^' -> {
                    currentPosition = nextPosition
                    totalVisited += currentPosition
                }

                '#' -> direction = direction.turn()
                null -> break
            }
        }

        return totalVisited.size
    }


    override fun part2(input: List<String>): Any {
        return -1
    }
}

fun main() {
    Day06.solve()
}

