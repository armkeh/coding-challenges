package io.github.armkeh

import java.io.File

val input = File("src/inputs/day03.txt").readText()

fun main() {
    Day03.part1(input.trimEnd { it == '\n' })
}