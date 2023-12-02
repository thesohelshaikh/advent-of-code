package day02

import Puzzle

object Day02 : Puzzle {
    private fun getGames(input: List<String>) = input.map { line ->
        val (gameName, setInfo) = line.split(":")
        val id = gameName.split(" ").last().toInt()
        val cubeSets = setInfo.split(";").map { set ->
            set.split(",").map {
                val (count, color) = it.trim().split(" ")
                Cube(count.toInt(), CubeColor.from(color))
            }
        }
        CubeGame(id = id, cubeSets)
    }

    override fun part1(input: List<String>): Any {
        val testSet = listOf(
            Cube(12, CubeColor.RED),
            Cube(13, CubeColor.GREEN),
            Cube(14, CubeColor.BLUE),
        )
        return getGames(input).filter { game ->
            game.cubeSet.all { cubeSet ->
                cubeSet.all { cube ->
                    testSet.any { it.color == cube.color && cube.count <= it.count }
                }
            }
        }.sumOf { it.id }
    }

    override fun part2(input: List<String>): Any {
        return getGames(input).sumOf { game ->
            val allCubes = game.cubeSet.flatten()
            val maxRed = allCubes.filter { it.color == CubeColor.RED }.maxOf { it.count }
            val maxGreen = allCubes.filter { it.color == CubeColor.GREEN }.maxOf { it.count }
            val maxBlue = allCubes.filter { it.color == CubeColor.BLUE }.maxOf { it.count }

            maxRed * maxGreen * maxBlue
        }
    }
}

data class CubeGame(
    val id: Int,
    val cubeSet: List<List<Cube>> = emptyList(),
)

data class Cube(
    val count: Int,
    val color: CubeColor,
)

enum class CubeColor(val value: String) {
    RED("red"), GREEN("green"), BLUE("blue");

    companion object {
        fun from(value: String): CubeColor {
            return when (value) {
                RED.value -> RED
                BLUE.value -> BLUE
                else -> GREEN
            }
        }
    }
}

fun main() {
    Day02.solve()
}