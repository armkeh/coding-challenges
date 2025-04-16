package io.github.armkeh

import java.io.File

val input = File("src/inputs/day01.txt").readText()

fun main() {
    Day01.part02(input.trimEnd { it == '\n' })
}