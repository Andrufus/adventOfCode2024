package days

class Day3 : Day(3u) {
    fun part1(): Int {
        return getSum(lines.joinToString())
    }

    fun part2(): Int {
        return getSum(
            Regex("do\\(\\).*?don't\\(\\)")
                .findAll("do()" + lines.joinToString() + "don't()")
                .map { matchResult -> matchResult.value }
                .joinToString()
        )
    }

    private fun getSum(line: String): Int {
        return Regex("mul\\((\\d+,\\d+)\\)").findAll(line)
            .map { matchResult -> matchResult.groupValues.last() }
            .map { s -> s.split(",").map { s2 -> s2.toInt() } }
            .map { numbers -> numbers.reduce(Int::times) }
            .sum()
    }
}