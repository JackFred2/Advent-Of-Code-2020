package problems

import misc.Utils

class Six : Problem {
    override fun partOne(): Int {
        val lines = Utils.loadFile("inputs/day6.txt")?.lines() ?: return -1
        val questions = mutableSetOf<Char>()
        var count = 0
        for (line in lines) {
            if (line.isEmpty()) {
                for (entry in questions) {
                    count++
                }
                questions.clear()
            } else {
                for (char in line) questions.add(char)
            }
        }
        return count
    }

    override fun partTwo(): Int {
        val lines = Utils.loadFile("inputs/day6.txt")?.lines() ?: return -1
        val questions = mutableMapOf<Char, Int>()
        var count = 0
        var lineCount = 0
        for (line in lines) {
            if (line.isEmpty()) {
                for (entry in questions) {
                    if (entry.value == lineCount) count++
                }
                lineCount = 0
                questions.clear()
            } else {
                lineCount++
                for (char in line) questions[char] = questions.getOrDefault(char, 0) + 1
            }
        }
        return count
    }
}