package day03

import Puzzle

object Day03: Puzzle {
    override fun part1(input: List<String>): Any {
        val partNumber = mutableListOf<PartialPartNumber>()
        input.forEachIndexed { lineIndex, s ->
            val hasNumber = s.firstOrNull { it.isDigit() } != null
            if (!hasNumber) {
                return@forEachIndexed
            }

            var startNumberIndex : Int ?= null
            var number = ""
            s.forEachIndexed { index, c ->
                if (c.isDigit()) {
                    if (startNumberIndex == null) {
                        startNumberIndex = index
                    }
                    number += c
                    if (index != s.lastIndex && s[index + 1].isDigit()) {
                        return@forEachIndexed
                    }
                }

                if (startNumberIndex != null) {
                    val prevIndex = startNumberIndex!! - 1
                    val nextIndex = index + 1
                    val prevLineIndex = lineIndex - 1
                    val nextLineIndex = lineIndex + 1

                    var hasSymbol = false
                    if (prevIndex > 0 && prevIndex < s.length) {
                        val prevChar = s[prevIndex]
                        if (prevChar != '.') {
                            hasSymbol = true
                        }
                    }

                    if (!hasSymbol && nextIndex < s.length) {
                        val nextChar = s[nextIndex]
                        if (nextChar != '.') {
                            hasSymbol = true
                        }
                    }

                    if (!hasSymbol && prevLineIndex > 0 && prevLineIndex < input.size) {
                        val startScan = if (startNumberIndex!! == 0) {
                            0
                        } else {
                           startNumberIndex!! - 1
                        }
                        val endScan = if (startNumberIndex!! + number.length >= s.length) {
                            s.length - 1
                        } else {
                            startNumberIndex!! + number.length
                        }
                        val prevLine = input[prevLineIndex]

                        for (scanIndex in startScan..endScan) {
                            if (prevLine[scanIndex] != '.' && !prevLine[scanIndex].isDigit()) {
                                hasSymbol = true
                                break
                            }
                        }
                    }

                    if (!hasSymbol && nextLineIndex < input.size) {
                        val startScan = if (startNumberIndex!! == 0) {
                            0
                        } else {
                           startNumberIndex!! - 1
                        }
                        val endScan = if (startNumberIndex!! + number.length >= s.length) {
                            s.length - 1
                        } else {
                            startNumberIndex!! + number.length
                        }
                        val nextLine = input[nextLineIndex]

                        for (scanIndex in startScan..endScan) {
                            if (nextLine[scanIndex] != '.' && !nextLine[scanIndex].isDigit()) {
                                hasSymbol = true
                                break
                            }
                        }
                    }

                    if (hasSymbol) {
                        partNumber.add(PartialPartNumber(lineIndex, startNumberIndex!!, number.toInt()))
                    }
                    startNumberIndex = null
                    number = ""
                }
            }

        }

        return partNumber.sumOf { it.value }
    }

    override fun part2(input: List<String>): Any {
        val gears = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()
        input.forEachIndexed { lineIndex, s ->
            val hasNumber = s.firstOrNull { it.isDigit() } != null
            if (!hasNumber) {
                return@forEachIndexed
            }

            var startNumberIndex : Int ?= null
            var number = ""
            s.forEachIndexed { index, c ->
                if (c.isDigit()) {
                    if (startNumberIndex == null) {
                        startNumberIndex = index
                    }
                    number += c
                    if (index != s.lastIndex && s[index + 1].isDigit()) {
                        return@forEachIndexed
                    }
                }

                if (startNumberIndex != null) {
                    val prevIndex = startNumberIndex!! - 1
                    val nextIndex = index + 1
                    val prevLineIndex = lineIndex - 1
                    val nextLineIndex = lineIndex + 1

                    var hasSymbol = false
                    var symbolY = 0
                    var symbolX = lineIndex
                    if (prevIndex > 0 && prevIndex < s.length) {
                        val prevChar = s[prevIndex]
                        if (prevChar == '*') {
                            symbolY = prevIndex
                            hasSymbol = true
                        }
                    }

                    if (!hasSymbol && nextIndex < s.length) {
                        val nextChar = s[nextIndex]
                        if (nextChar == '*') {
                            symbolY = nextIndex
                            hasSymbol = true
                        }
                    }

                    if (!hasSymbol && prevLineIndex > 0 && prevLineIndex < input.size) {
                        val startScan = if (startNumberIndex!! == 0) {
                            0
                        } else {
                            startNumberIndex!! - 1
                        }
                        val endScan = if (startNumberIndex!! + number.length >= s.length) {
                            s.length - 1
                        } else {
                            startNumberIndex!! + number.length
                        }
                        val prevLine = input[prevLineIndex]

                        for (scanIndex in startScan..endScan) {
                            if (prevLine[scanIndex] == '*') {
                                hasSymbol = true
                                symbolY = scanIndex
                                symbolX = prevLineIndex
                                break
                            }
                        }
                    }


                    if (!hasSymbol && nextLineIndex < input.size) {
                        val startScan = if (startNumberIndex!! == 0) {
                            0
                        } else {
                            startNumberIndex!! - 1
                        }
                        val endScan = if (startNumberIndex!! + number.length >= s.length) {
                            s.length - 1
                        } else {
                            startNumberIndex!! + number.length
                        }
                        val nextLine = input[nextLineIndex]

                        for (scanIndex in startScan..endScan) {
                            if (nextLine[scanIndex] == '*') {
                                hasSymbol = true
                                symbolY = scanIndex
                                symbolX = nextLineIndex
                                break
                            }
                        }
                    }

                    if (hasSymbol) {
                        val coordinates = gears[Pair(symbolX, symbolY)]
                        if (coordinates.isNullOrEmpty()) {
                            gears[Pair(symbolX, symbolY)] = mutableListOf(number.toInt())
                        } else {
                            coordinates.add(number.toInt())
                        }
                    }
                    startNumberIndex = null
                    number = ""
                }
            }

        }

        return gears.filter { it.value.size == 2 }.map {
            it.value.fold(1, Int::times)
        }.sum()
    }
}

data class PartialPartNumber(
    val lineIndex: Int,
    val index: Int,
    val value: Int,
)

fun main() {
    Day03.solve()
}