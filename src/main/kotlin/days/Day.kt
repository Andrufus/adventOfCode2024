package days

import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path

private const val INPUTS_DIRECTORY = "inputs"

abstract class Day {
    protected val lines: List<String> = Files.lines(getInputPath(1u)).toList()

    private fun getInputPath(dayNumber: UInt): Path {
        return FileSystems.getDefault().getPath("$INPUTS_DIRECTORY/day$dayNumber.txt").toAbsolutePath()
    }
}