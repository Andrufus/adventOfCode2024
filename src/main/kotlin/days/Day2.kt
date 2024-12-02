package days

class Day2 : Day(2u) {
    private val reports: List<List<Int>> = lines.map { line -> line.split(" ").map { s -> s.toInt() } }

    fun part1(): Int {
        return reports.count { report -> isSafe(report) }
    }

    fun part2(): Int {
        return reports.count { report -> genVersions(report).any { version -> isSafe(version) } }
    }

    private fun isSafe(report: List<Int>): Boolean {
        var isSafe = true
        val increasing = report.first() < report.last()

        report.forEachIndexed { index, level ->
            if (index == 0) return@forEachIndexed

            val diff = if (increasing) level - report[index - 1] else report[index - 1] - level
            if (diff < 1 || diff > 3) {
                isSafe = false
                return@forEachIndexed
            }
        }

        return isSafe
    }

    private fun genVersions(report: List<Int>): List<List<Int>> {
        return (0 until report.count()).map { i -> report.filterIndexed { j, _ -> j != i } }.toList()
    }
}