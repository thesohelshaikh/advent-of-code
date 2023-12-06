package day05

import Puzzle

object Day05: Puzzle {
    override fun part1(input: List<String>): Any {
        if (input.isEmpty()) return 0
        val seeds = input.first().split(": ")[1].split(" ").map { it.trim().toLong() }

        val seedMapList = input.asSequence().mapIndexed { index, s ->
            if (s.isEmpty()) index
            else -1
        }.filterNot { it == -1  }.windowed(2).map {
            IntRange(it[0] + 2, it[1] - 1)
        }.toMutableList()
        seedMapList.add(IntRange(seedMapList.last().last + 3, input.size - 1))

        val maps = mutableListOf<MutableList<Triple<Long, Long, Long>>>()
        seedMapList.forEach {
            val sdMap = mutableListOf<Triple<Long, Long, Long>>()
            for (i in it) {
                val splitted = input[i].split(" ").map { it.trim().toLong() }
                val destinationStart = splitted[0]
                val sourceStart = splitted[1]
                val range = splitted[2]
                sdMap.add(Triple(destinationStart, sourceStart, range))
            }
            maps.add(sdMap)
        }
        val finalMap = mutableListOf<List<Long>>()
        seeds.forEach { seed ->
            var criteria = seed
            var mutableList = mutableListOf<Long>()
            maps.forEach { map ->
                var loc : Long? = null
               mutableList = mutableListOf()
                run loop@   {
                    map.forEach {
                        if (criteria in it.second..<it.second + it.third) {
                            loc = criteria - it.second + it.first
                            criteria = loc as Long
                            return@loop
                        }
                    }
                }

                if (loc == null) {
                    loc = criteria
                    criteria = loc as Long
                }
                mutableList.add(loc!!)
            }
            finalMap.add(mutableList)
        }



        return finalMap.flatten().min()
    }

    override fun part2(input: List<String>): Any {
        if (input.isEmpty()) return 0
        val seedsInput = input.first().split(": ")[1].split(" ").map { it.trim().toLong() }

        val seeds = seedsInput.chunked(2)
            .map { LongRange(it.first(), it.first() + it.last()).toList() }
            .flatten()



       val seedMapList = input.asSequence().mapIndexed { index, s ->
            if (s.isEmpty()) index
            else -1
        }.filterNot { it == -1  }.windowed(2).map {
            IntRange(it[0] + 2, it[1] - 1)
        }.toMutableList()
        seedMapList.add(IntRange(seedMapList.last().last + 3, input.size - 1))

        val maps = mutableListOf<MutableList<Triple<Long, Long, Long>>>()
        seedMapList.forEach {
            val sdMap = mutableListOf<Triple<Long, Long, Long>>()
            for (i in it) {
                val splitted = input[i].split(" ").map { it.trim().toLong() }
                val destinationStart = splitted[0]
                val sourceStart = splitted[1]
                val range = splitted[2]
                sdMap.add(Triple(destinationStart, sourceStart, range))
            }
            maps.add(sdMap)
        }
        val finalMap = mutableListOf<List<Long>>()
        seeds.forEach { seed ->
            var criteria = seed
            var mutableList = mutableListOf<Long>()
            maps.forEach { map ->
                var loc : Long? = null
                mutableList = mutableListOf()
                run loop@   {
                    map.forEach {
                        if (criteria in it.second..<it.second + it.third) {
                            loc = criteria - it.second + it.first
                            criteria = loc as Long
                            return@loop
                        }
                    }
                }

                if (loc == null) {
                    loc = criteria
                    criteria = loc as Long
                }
                mutableList.add(loc!!)
            }
            finalMap.add(mutableList)
        }



        return finalMap.flatten().min()
    }
}

data class Seed(
    val id: Int,
    val soil: Int,
    val fertilizer: Int,
    val water: Int,
    val light: Int,
    val temperature: Int,
    val humidity: Int,
    val location: Int,
)

fun main() {
    Day05.solve()
}