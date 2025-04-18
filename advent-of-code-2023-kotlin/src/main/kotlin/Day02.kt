package io.github.armkeh

import kotlin.math.max

object Day02 {
    fun part01(input: String) {
        val exceedingPulls = input.lines().foldIndexed(0) { gameNum, sum, game ->
            val hasExceedingPull =
                game.dropWhile { it !=  ':' }.drop(1)
                    .split(';')
                    .any { it.isExceedingPull() }

            sum + if (hasExceedingPull) 0 else gameNum+1
        }

        println("Sum of game numbers with pulls that exceed the provided cubes is $exceedingPulls.")
    }

    fun part02(input: String) {
        val sumOfPowers = input.lines().sumOf { game ->
            game.dropWhile { it != ':' }.drop(1)
                .split(';', ',')
                .fold(Triple(0,0,0)) { (redPower, greenPower, bluePower), pull ->
                    val count = pull.filter { it.isDigit() }.toInt()
                    when {
                        pull.contains("red")   -> Triple(max(redPower, count), greenPower, bluePower)
                        pull.contains("green") -> Triple(redPower, max(greenPower, count), bluePower)
                        pull.contains("blue")  -> Triple(redPower, greenPower, max(bluePower, count))
                        else -> throw IllegalArgumentException("Invalid pull entry encountered: $pull")
                    }
                }.let { (redPower, greenPower, bluePower) -> redPower * greenPower * bluePower }
        }

        println("Sum of the cube powers of each game is $sumOfPowers.")
    }

    private fun String.isExceedingPull(): Boolean = this.let { pull ->
        pull.split(",").any { pull ->
            val count = pull.filter { it.isDigit() }.toInt()

            when {
                pull.contains("red")   -> count > 12
                pull.contains("green") -> count > 13
                pull.contains("blue")  -> count > 14
                else -> throw IllegalArgumentException("Invalid pull entry encountered: $pull")
            }
        }
    }
}