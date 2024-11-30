package day04

import Solution

object Day04 : Solution {

    data class BingoNumber(val number: Int, var isMarked: Boolean = false)

    class BingoBoard {
        var isWinner = false
        private var winningNumbers = -1
        var score = -1

        val numbers: ArrayList<MutableList<BingoNumber>> = ArrayList()

        fun mark(number: Int) {
            numbers.forEach {
                it.firstOrNull { it.number == number }?.isMarked = true
            }

            if (checkIsWinner()) {
                winningNumbers = number
                calculateScore()
            }
        }

        private fun calculateScore() {
            var sum = 0
            numbers.forEach { row ->
                row.forEach {
                    if (!it.isMarked) {
                        sum += it.number
                    }
                }
            }
            score = sum * winningNumbers
        }

        private fun checkIsWinner(): Boolean {
            if (checkColumn()) return true

            if (checkRow()) return true

            return false
        }

        private fun checkRow(): Boolean {
            for (row in 0 until 5) {
                var isChecked = 0
                for (col in 0 until 5) {
                    if (numbers[col][row].isMarked) {
                        isChecked++
                    }
                }
                if (isChecked == 5) {
                    isWinner = true
                    return true
                }
            }
            return false
        }

        private fun checkColumn(): Boolean {
            numbers.forEach { row ->
                if (row.all { it.isMarked }) {
                    isWinner = true
                    return true
                }
            }
            return false
        }

    }

    var boards = ArrayList<BingoBoard>()

    private fun parseBoards(input: List<String>) {
        val cleanedInput = input.toMutableList()
        cleanedInput.removeAt(0)
        cleanedInput
            .chunked(6)
            .forEach { boardInput ->
                val board = BingoBoard()
                boardInput.forEach { line ->
                    if (line.isNotEmpty() && line.isNotBlank()) {
                        val numbers = line.split(" ")
                            .filter { it.isNotEmpty() }
                            .map { it.toInt() }
                            .map { BingoNumber(it) }.toMutableList()
                        board.numbers.add(numbers)
                    }
                }
                boards.add(board)
            }
    }

    override fun part1(input: List<String>): Any {
        val drawnNumbers = input.first().split(",").map { it.toInt() }
        boards.clear()
        parseBoards(input)

        return callNumbers(drawnNumbers)
    }

    private fun callNumbers(drawnNumbers: List<Int>): Int {
        drawnNumbers.forEach { number: Int ->
            boards.forEachIndexed { index, bingoBoard ->
                bingoBoard.mark(number)

                if (bingoBoard.isWinner) {
                    return bingoBoard.score
                }
            }
        }
        return -1
    }

    override fun part2(input: List<String>): Any {
        val drawnNumbers = input.first().split(",").map { it.toInt() }
        boards.clear()
        parseBoards(input)

        return callNumbersTwo(drawnNumbers)
    }

    private fun callNumbersTwo(drawnNumbers: List<Int>): Int {
        var score = -1
        drawnNumbers.forEach { number: Int ->
            val iterator = boards.iterator()

            while (iterator.hasNext()) {
                val board = iterator.next()
                board.mark(number)

                if (board.isWinner) {
                    score = board.score
                    iterator.remove()
                }
            }
        }
        return score
    }
}

fun main() {
    Day04.solve()
}