package org.example

import java.util.*
import kotlin.system.measureTimeMillis

interface Puzzle {

    fun getYear(): String {
        return "2024"
    }

    fun part1(input: List<String>): Any

    fun part2(input: List<String>): Any

    fun solve() {
        val year = getYear()
        val className = this::class.simpleName.toString()
        val packageName = className.lowercase(Locale.getDefault())
        val path = "$year/src/main/kotlin/$packageName/$className"
        val testPath = "$year/src/main/kotlin/$packageName/${className}_test"
        println("Test output")
        println("-----------")
        val partOneTestTime = measureTimeMillis {
            print("part1: " + part1(readInput(testPath)))
        }
        println(" Solved in $partOneTestTime ms")
        val partTwoTestTime = measureTimeMillis {
            print("part2: " + part2(readInput(testPath)))
        }
        println(" Solved in $partTwoTestTime ms")
        println("-----------")
        println("Solution")
        println("-----------")
        val partOneTime = measureTimeMillis {
            print("part1: " + part1(readInput(path)))
        }
        println(" Solved in $partOneTime ms")
        val partTwoTime = measureTimeMillis {
            print("part2: " + part2(readInput(path)))
        }
        println(" Solved in $partTwoTime ms")
        println("-----------")
    }
}