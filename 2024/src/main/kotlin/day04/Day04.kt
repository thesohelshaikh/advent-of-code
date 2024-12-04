package day04

import org.example.Puzzle

object Day04 : Puzzle {
    private enum class Direction(val dx: Int, val dy: Int) {
        NORTH(0, -1),
        NORTHEAST(1, -1),
        EAST(1, 0),
        SOUTHEAST(1, 1),
        SOUTH(0, 1),
        SOUTHWEST(-1, 1),
        WEST(-1, 0),
        NORTHWEST(-1, -1);

        operator fun component1() = dx
        operator fun component2() = dy
    }

    private fun List<String>.getLetter(x: Int, y: Int): Char? = getOrNull(y)?.getOrNull(x)

    override fun part1(input: List<String>): Any {
        val word = "XMAS"

        return input.indices.sumOf { y ->
            input[y].indices.sumOf { x ->
                Direction.entries.count { (dx, dy) ->
                    word.indices.all { i ->
                        input.getLetter(x + i * dx, y + i * dy) == word[i]
                    }
                }
            }
        }
    }

    override fun part2(input: List<String>): Int {
        val diagonals = listOf(
            Direction.NORTHWEST to Direction.SOUTHEAST,
            Direction.SOUTHWEST to Direction.NORTHEAST,
        )

        return input.indices.sumOf { y ->
            input[y].indices.count { x ->
                if (input.getLetter(x, y) != 'A') return@count false

                diagonals.all { (d1, d2) ->
                    val c1 = input.getLetter(x + d1.dx, y + d1.dy)
                    val c2 = input.getLetter(x + d2.dx, y + d2.dy)
                    (c1 == 'M' && c2 == 'S') || (c1 == 'S' && c2 == 'M')
                }
            }
        }
    }
}

fun main() {
    Day04.solve()
}