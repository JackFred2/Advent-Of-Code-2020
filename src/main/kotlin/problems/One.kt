package problems

import misc.Utils

class One : Problem {
    override fun partOne(): Int {
        val input = Utils.loadFile("inputs/day1.txt")?.lines() ?: return -1
        val values = input.map { it.toInt() }
        for (a in values.indices) for (b in a + 1 until values.size) {
            if (values[a] + values[b] == 2020) return values[a] * values[b]
        }
        return 0
    }

    override fun partTwo(): Int {
        val input = Utils.loadFile("inputs/day1.txt")?.lines() ?: return -1
        val values = input.map { it.toInt() }
        for (a in values.indices) for (b in a + 1 until values.size) for (c in b + 1 until values.size) {
            if (values[a] + values[b] + values[c] == 2020) return values[a] * values[b] * values[c]
        }
        return 0
    }
}