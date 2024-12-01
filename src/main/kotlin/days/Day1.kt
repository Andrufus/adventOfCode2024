package days

import kotlin.math.abs

class Day1 : Day() {
    private val list1 = mutableListOf<Int>()
    private val list2 = mutableListOf<Int>()

    init {
        lines.forEach { line ->
            val numbers = line.split("   ").map { s -> s.toInt() }
            list1.add(numbers[0])
            list2.add(numbers[1])
        }
    }

    fun part1(): Int {
        list1.sort()
        list2.sort()

        return list1.mapIndexed { i, number ->
            abs(number - list2[i])
        }.sum()
    }

    fun part2(): Int {
        return list1.sumOf { number ->
            number * list2.count { n -> n == number }
        }
    }
}