package io.github.armkeh

import java.io.File

val input = File("src/inputs/day02.txt").readText()

fun main() {
    Day02.part02(input.trimEnd { it == '\n' })
}