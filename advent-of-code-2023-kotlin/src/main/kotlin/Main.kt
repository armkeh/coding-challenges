package io.github.armkeh

import java.io.File

val day01Input = File("src/inputs/day01.txt")

fun day01Part01(input: List<String>) {
    val sum = input.sumOf { line ->
        val tens = line.first { it.isDigit() }.toString().toInt()
        val ones = line.last  { it.isDigit() }.toString().toInt()

        10 * tens + ones
    }

    println("Sum of calibration values is $sum")
}

fun day01Part02(input: List<String>) {
    // Because some letters may be shared between two adjacent digit words (e.g., eightwo),
    // to solve this using replacement, we must preserve those letters
    // while also inserting the numerals for the words.
    // The simplest approach is to place two copies of the word, around the numeral.
    // We could be more clever and determine which letters are strictly needed, but no need to over-optimize.
    val revisedInput = input.map { it
        .replace("one",   "one1one", ignoreCase = true)
        .replace("two",   "two2two", ignoreCase = true)
        .replace("three", "three3three", ignoreCase = true)
        .replace("four",  "four4four", ignoreCase = true)
        .replace("five",  "five5five", ignoreCase = true)
        .replace("six",   "six6six", ignoreCase = true)
        .replace("seven", "seven7seven", ignoreCase = true)
        .replace("eight", "eight8eight", ignoreCase = true)
        .replace("nine",  "nine9nine", ignoreCase = true)
        // "zero" was not identified as a valid digit word.
        // .replace("zero",  "0", ignoreCase = true)
    }

    day01Part01(revisedInput)
}

fun main() {
    day01Part02(day01Input.readLines())
}