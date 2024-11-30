package day05

import Solution
import kotlin.math.absoluteValue
import kotlin.math.sign

object Day05 : Solution {

    data class Point(val x: Int, val y: Int)
    data class Line(val start: Point, val end: Point) {

        /**
         * Returns all the points on a line including starting and ending point.
         */
        fun getAllPoints(includeDiagonals: Boolean = false): ArrayList<Point> {
            val points = ArrayList<Point>()

            // horizontal line
            if (start.x == end.x) {
                for (i in minOf(start.y, end.y)..maxOf(start.y, end.y)) {
                    points.add(Point(start.x, i))
                }
                return points
            }

            // vertical line
            if (start.y == end.y) {
                for (i in minOf(start.x, end.x)..maxOf(start.x, end.x)) {
                    points.add(Point(i, start.y))
                }
                return points
            }

            if (includeDiagonals) {
                val dx = (end.x - start.x).sign
                val dy = (end.y - start.y).sign
                for (t in 0..maxOf((start.x - end.x).absoluteValue, (start.y - end.y).absoluteValue))
                    points.add(Point(start.x + t * dx, start.y + t * dy))
                return points
            }

            return ArrayList()
        }
    }

    private fun buildGrid(lines: List<Line>, includeDiagonals: Boolean = false): Triple<Int, Int, Array<IntArray>> {
        val width = lines.maxOf { line -> maxOf(line.start.x, line.end.x) }
        val height = lines.maxOf { line -> maxOf(line.start.y, line.end.y) }

        val grid = Array(width + 1) { IntArray(height + 1) }

        lines.forEach { line ->
            val points = line.getAllPoints(includeDiagonals)
            points.forEach {
                grid[it.x][it.y]++
            }
        }
        return Triple(width, height, grid)
    }

    private fun calculateIntersections(
        width: Int,
        height: Int,
        grid: Array<IntArray>
    ): Int {
        var numIntersections = 0
        for (x in 0..width) {
            for (y in 0..height) {
                if (grid[x][y] > 1) {
                    numIntersections++
                }
            }
        }
        return numIntersections
    }

    override fun part1(input: List<String>): Any {
        val lines = input.map {
            val (start, end) = it.split(" -> ")
            val (x1, y1) = start.split(",")
            val (x2, y2) = end.split(",")
            Line(Point(x1.toInt(), y1.toInt()), Point(x2.toInt(), y2.toInt()))
        }

        val (width, height, grid) = buildGrid(lines)

        return calculateIntersections(width, height, grid)
    }

    override fun part2(input: List<String>): Any {
        val lines = input.map {
            val (start, end) = it.split(" -> ")
            val (x1, y1) = start.split(",")
            val (x2, y2) = end.split(",")
            Line(Point(x1.toInt(), y1.toInt()), Point(x2.toInt(), y2.toInt()))
        }

        val (width, height, grid) = buildGrid(lines, true)

        return calculateIntersections(width, height, grid)
    }
}

fun main() {
    Day05.solve()
}