package days

import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path

private const val INPUTS_DIRECTORY = "inputs"

abstract class Day(number: UInt) {
    protected val lines: List<String> = Files.lines(getInputPath(number)).toList()

    private fun getInputPath(dayNumber: UInt): Path {
        return FileSystems.getDefault().getPath("$INPUTS_DIRECTORY/day$dayNumber.txt").toAbsolutePath()
    }
}