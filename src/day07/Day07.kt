package day07

import Puzzle

object Day07: Puzzle {
    override fun part1(input: List<String>): Any {
        val hands = input.map {
            val cardsString = it.substringBefore(" ").toCharArray().toList()
            val rank = it.substringAfter(" ").toLong()
            Hand(cardsString, rank)
        }

        val clubbedByStrength = hands.map { hand ->
            hand to hand.cards.groupingBy { it }.eachCount().toList().groupingBy { it.second }.eachCount().toSortedMap().toList().last()
        }.sortedBy { it.second.first }.groupBy { it.second.first }

        val sortedHands = mutableListOf<Hand>()

        clubbedByStrength.forEach {subList ->
            val intermediate = mutableListOf<Hand>()
            val lastIndex = subList.value.lastIndex
            subList.value.forEachIndexed { index, item ->
                val nextIndex = index + 1
                if (index >= lastIndex)  {
                    intermediate.add(item.first)
                    val sorted = intermediate.sort()
                    sortedHands.addAll(sorted)
                    intermediate.clear()
                    return@forEachIndexed
                }

                val next = subList.value.get(nextIndex).second

                if (item.second.second < next.second) {
                    sortedHands.add(item.first)
                } else if (item.second.second == next.second) {
                    intermediate.add(item.first)
                } else {
                    val sorted = intermediate.sort()
                    sortedHands.addAll(sorted)
                    intermediate.clear()
                }

            }
        }

        val sum = sortedHands.mapIndexed { index, hand ->
            hand to index + 1
        }.map {
            it.first.rank * it.second
        }.sum()

        return sum
    }

    override fun part2(input: List<String>): Any {
        return 0
    }
}


val strengthMap = mapOf(
    'A' to 'm',
    'K' to 'l',
    'Q' to 'k',
    'J' to 'j',
    'T' to 'i',
    '9' to 'h',
    '8' to 'g',
    '7' to 'f',
    '6' to 'e',
    '5' to 'd',
    '4' to 'c',
    '3' to 'b',
    '2' to 'a',
)
data class Hand(
    val cards: List<Char>,
    val rank: Long,
)

fun List<Hand>.sort(): List<Hand> {
     return this.sortedBy {
         it.cards.map { strengthMap[it] }.toString()
     }
}

fun main() {
    Day07.solve()
}