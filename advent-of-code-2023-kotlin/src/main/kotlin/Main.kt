package io.github.armkeh

val input = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
        """.trimIndent()

fun main() {
    val sum = input.lines().sumOf { line ->
        val tens = line.first { it.isDigit() }.toString().toInt()
        val ones = line.last  { it.isDigit() }.toString().toInt()

        10 * tens + ones
    }

    println("Sum of calibration values is $sum")
}