package day05

import org.example.Puzzle

object Day05 : Puzzle {

    data class UpdateInfo(
        val ruleComparator: Comparator<Int>,
        val updatesToProcess: List<List<Int>>
    )

    private fun createComparator(rules: List<Pair<Int, Int>>): Comparator<Int> {
        val ruleMap = rules.associateWith { -1 } + rules.map { it.second to it.first }.associateWith { 1 }
        return Comparator { a, b -> ruleMap.getOrDefault(a to b, 0) }
    }

    private fun parse(input: List<String>): UpdateInfo {
        val partition = input.partition { it.contains("|") }
        val rules = partition.first.map {
            val (l, r) = it.split("|").map { it.toInt() }
            Pair(l, r)
        }
        val updates: List<List<Int>> =
            partition.second.filter { it.isNotEmpty() }.map { it.split(",").map { it.toInt() } }
        return UpdateInfo(createComparator(rules), updates)
    }

    override fun part1(input: List<String>): Any {
        val info = parse(input)

        return info.updatesToProcess
            .filter { it.sortedWith(info.ruleComparator) == it }
            .sumOf { it[it.count() / 2] }
    }

    override fun part2(input: List<String>): Any {
        val info = parse(input)

        return info.updatesToProcess.filter { it.sortedWith(info.ruleComparator) != it }
            .map { it.sortedWith(info.ruleComparator) }
            .sumOf { it[it.count() / 2] }
    }
}

fun main() {
    Day05.solve()
}