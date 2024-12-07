package days

import org.paukov.combinatorics3.Generator

class Day7 : Day(7u) {
    enum class Op {
        ADD, MUL
    }

    enum class Op2 {
        ADD, MUL, CON
    }

    private val permutationsByLength = (3..12).associateWith { l ->
        Generator.permutation(Op.ADD, Op.MUL).withRepetitions(l).toList()
    }

    private val permutationsByLength2 = (3..12).associateWith { l ->
        Generator.permutation(Op2.ADD, Op2.MUL, Op2.CON).withRepetitions(l).toList()
    }

    private val equations = lines.map { line ->
        val parts = line.split(": ")
        Pair(parts[0].toLong(), parts[1].split(' ').map { n -> n.toLong() })
    }

    fun part1(): Long {
        return equations.filter { eq ->
            permutationsByLength[eq.second.size]?.any { ops -> compute(eq.second, ops) == eq.first } == true
        }.sumOf { eq -> eq.first }
    }

    fun part2(): Long {
        return equations.filter { eq ->
            permutationsByLength2[eq.second.size]?.any { ops -> compute2(eq.second, ops) == eq.first } == true
        }.sumOf { eq -> eq.first }
    }

    private fun compute(numbers: List<Long>, operations: List<Op>): Long {
        return numbers.reduceIndexed { index, acc, l ->
            when (operations[index]) {
                Op.ADD -> acc + l
                Op.MUL -> acc * l
            }
        }
    }

    private fun compute2(numbers: List<Long>, operations: List<Op2>): Long {
        return numbers.reduceIndexed { index, acc, l ->
            when (operations[index]) {
                Op2.ADD -> acc + l
                Op2.MUL -> acc * l
                Op2.CON -> "$acc$l".toLong()
            }
        }
    }
}