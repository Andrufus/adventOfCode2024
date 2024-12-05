package days

import kotlin.math.ceil

class Day5 : Day(5u) {
    private val rules = lines.dropLast(198).map { rule ->
        val pages = rule.split('|').map { s -> s.toInt() }
        Pair(pages.first(), pages.last())
    }

    private val updates = lines.drop(1177).map { rule -> rule.split(',').map { s -> s.toInt() } }

    fun part1(): Int {
        return updates.filter { update -> isValid(update) }.sumOf { update -> getMiddlePage(update) }
    }

    fun part2(): Int {
        return updates.filterNot { update -> isValid(update) }.map { update ->
            val validUpdate = update.toMutableList()
            val rules = getRules(update)

            var invalidRules = getInvalidRules(validUpdate, rules)
            while (invalidRules.isNotEmpty()) {
                validUpdate.swap(
                    validUpdate.indexOf(invalidRules[0].first),
                    validUpdate.indexOf(invalidRules[0].second)
                )
                invalidRules = getInvalidRules(validUpdate, rules)
            }

            validUpdate
        }.sumOf { update -> getMiddlePage(update) }
    }

    private fun getMiddlePage(update: List<Int>) = update[ceil((update.size / 2).toDouble()).toInt()]

    private fun getRules(update: List<Int>) = rules.filter { pair -> update.containsAll(pair.toList()) }

    private fun getInvalidRules(update: List<Int>, rules:  List<Pair<Int, Int>>) =
        rules.filter { rule -> update.indexOf(rule.first) > update.indexOf(rule.second) }

    private fun isValid(update: List<Int>) =
        getRules(update).all { rule -> update.indexOf(rule.first) < update.indexOf(rule.second) }

    private fun <T> MutableList<T>.swap(idx1: Int, idx2: Int): MutableList<T> = apply {
        val t = this[idx1]
        this[idx1] = this[idx2]
        this[idx2] = t
    }
}