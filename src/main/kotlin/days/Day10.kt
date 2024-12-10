package days

class Day10 : Day(10u) {
    class Position(val lineIndex: Int, val col: Int, val n: Int)

    private val map = lines.map { l ->
        l.toList().map { char -> char.digitToInt() }.toTypedArray()
    }.toTypedArray()

    private val tops: MutableList<Position> = mutableListOf()

    fun part1(): Int {
        var score = 0
        map.forEachIndexed { lineIndex, line ->
            line.forEachIndexed l@{ col, n ->
                if (n != 0) return@l

                findPath(lineIndex, col, n)
                score += tops.distinctBy { position -> "${position.lineIndex}${position.col}" }.size
                tops.clear()
            }
        }

        return score
    }

    fun part2(): Int {
        map.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { col, n ->
                if (n == 0) {
                    findPath(lineIndex, col, n)
                }
            }
        }

        return tops.size
    }

    private fun findPath(lineIndex: Int, col: Int, n: Int) {
        val up = if (lineIndex > 0) Position(lineIndex - 1, col, map[lineIndex - 1][col]) else null
        val down = if (lineIndex < map[0].size - 1) Position(lineIndex + 1, col, map[lineIndex + 1][col]) else null
        val left = if (col > 0) Position(lineIndex, col - 1, map[lineIndex][col - 1]) else null
        val right = if (col < map[0].size - 1) Position(lineIndex, col + 1, map[lineIndex][col + 1]) else null

        listOfNotNull(up, down, left, right).filter { position -> position.n == n + 1 }.forEach { p ->
            if (p.n == 9) {
                tops.add(p)
            } else {
                findPath(p.lineIndex, p.col, p.n)
            }
        }
    }
}