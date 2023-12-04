package day04

import Puzzle

object Day04 : Puzzle {
    private fun getCards(input: List<String>): List<Card> {
        val cards = input.map { cardStr ->
            val (gameName, cardInfo) = cardStr.split(":")
            val id = gameName.split(" ").last().toInt()
            val numbers = cardInfo.split("|")
            val winningNumbers = getNumbersFromString(numbers[0])
            val cardNumbers = getNumbersFromString(numbers[1])
            Card(id, winningNumbers, cardNumbers)
        }
        return cards
    }

    private fun getNumbersFromString(numbers: String): List<Int> {
        return numbers.trim().split(" ").filterNot { it.isEmpty() || it.isBlank() }.map { it.toInt() }
    }

    override fun part1(input: List<String>): Any {
        val cards = getCards(input)
        return cards.sumOf { it.getPoints() }
    }

    override fun part2(input: List<String>): Any {
        val cards = getCards(input)
        var totalWonCards = 0

        cards.forEach { card ->
            var cardsToMatch = mutableListOf<Card>().apply {
                addAll(card.getWonCards(cards))
            }

            totalWonCards += cardsToMatch.size

            var wonCardsSize: Int
            do {
                val wonCards = cardsToMatch.getWonCards(cards)
                wonCardsSize = wonCards.size
                cardsToMatch = wonCards
                totalWonCards += wonCardsSize
            } while (wonCardsSize != 0)
        }
        return totalWonCards + cards.size
    }
}

data class Card(
    val id: Int,
    val cardNumbers: List<Int>,
    val winningNumbers: List<Int>,
) {
    fun getPoints(): Int {
        val count = getNumMatches()

        return if (count == 0.0) {
            0
        } else {
            Math.pow(2.0, count - 1).toInt()
        }
    }

    private fun getNumMatches() = winningNumbers.intersect(cardNumbers.toSet()).count().toDouble()

    fun getWonCards(originalCards: List<Card>): MutableList<Card> {
        val wonCards = mutableListOf<Card>()
        val matches = getNumMatches().toInt()
        for (i in (id)..<id + matches) {
            if (i <= originalCards.lastIndex) wonCards.add(originalCards[i])
        }
        return wonCards
    }
}

fun List<Card>.getWonCards(originalCards: List<Card>): MutableList<Card> {
    val wonCards = mutableListOf<Card>()
    this.forEachIndexed { index, card ->
        wonCards.addAll(card.getWonCards(originalCards))
    }
    return wonCards
}

fun main() {
    Day04.solve()
}