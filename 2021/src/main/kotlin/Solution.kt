import java.util.*

interface  Solution {

    fun part1(input: List<String>): Any

    fun part2(input: List<String>): Any

    fun solve() {
        val className = this::class.simpleName.toString()
        val packageName = className.lowercase(Locale.getDefault())
        val path = "$packageName/$className"
        val testPath = "$packageName/${className}_test"
        println("Test output")
        println("-----------")
        println(part1(readInput(testPath)))
        println(part2(readInput(testPath)))
        println("-----------")
        println("Solution")
        println("-----------")
        println(part1(readInput(path)))
        println(part2(readInput(path)))
        println("-----------")
    }
}