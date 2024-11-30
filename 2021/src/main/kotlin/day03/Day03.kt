package day03

import Solution
import toDecimal

object Day03 : Solution {

    private fun countZeroOne(input: List<String>): ArrayList<Pair<Int, Int>> {
        val numberOfZeroOne = ArrayList<Pair<Int, Int>>()

        for (i in 1..input.first().length) {
            numberOfZeroOne.add(Pair(0, 0))
        }

        input.forEach { number ->
            number.forEachIndexed { index, bit ->
                val currentCount = numberOfZeroOne[index]
                if (bit == '0') {
                    numberOfZeroOne[index] = currentCount.copy(first = currentCount.first + 1)
                } else {
                    numberOfZeroOne[index] = currentCount.copy(second = currentCount.second + 1)
                }

            }
        }
        return numberOfZeroOne
    }

    override fun part1(input: List<String>): Any {
        val numberOfZeroOne = countZeroOne(input)

        var gammaRate = ""
        var epsilonRate = ""
        numberOfZeroOne.forEach { pair ->
            if (pair.first > pair.second) {
                gammaRate += "0"
                epsilonRate += "1"
            } else {
                gammaRate += "1"
                epsilonRate += "0"
            }

        }

        return gammaRate.toDecimal() * epsilonRate.toDecimal()
    }

    override fun part2(input: List<String>): Any {
        val oxygenRating = getRating(input)
        val carbonRating = getRating(input, false)

        return oxygenRating * carbonRating
    }

    /**
     * Returns oxygen rating or carbon rating based on [ratingFor].
     *
     * @param ratingFor Pass true for oxygen, false for carbon
     */
    private fun getRating(input: List<String>, ratingFor: Boolean = true): Int {
        var numberOfZeroOne: ArrayList<Pair<Int, Int>>
        var filteredNumbers = input

        for (index in 0 until input.first().length) {
            if (filteredNumbers.size > 1) {
                numberOfZeroOne = countZeroOne(filteredNumbers)
                val zeroOneFreq = numberOfZeroOne[index]

                val checkBit = if (ratingFor) {
                    if (zeroOneFreq.first > zeroOneFreq.second) '0' else '1'
                } else {
                    if (zeroOneFreq.first <= zeroOneFreq.second) '0' else '1'
                }

                filteredNumbers = filteredNumbers.filter { it[index] == checkBit }
            }
        }
        return filteredNumbers.first().toDecimal()
    }
}

fun main() {
    Day03.solve()
}