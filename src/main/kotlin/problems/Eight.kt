package problems

import misc.ASMMachine
import misc.Utils

class Eight : Problem {
    override fun partOne(): Int {
        val lines = Utils.loadFile("inputs/day8.txt")?.lines() ?: return -1
        val machine = ASMMachine(lines)
        val visited = mutableSetOf<Int>()
        while (!visited.contains(machine.cursor)) {
            visited.add(machine.cursor)
            machine.cycle()
        }
        return machine.acc
    }

    private fun doesRunToCompletion(machine: ASMMachine): Boolean {
        val visited = mutableSetOf<Int>()
        while (!visited.contains(machine.cursor)) {
            visited.add(machine.cursor)
            machine.cycle()
            if (machine.stopped()) return true
        }
        return false
    }

    override fun partTwo(): Int {
        val lines = Utils.loadFile("inputs/day8.txt")?.lines() ?: return -1
        val machine = ASMMachine(lines)
        val triedChanging = mutableSetOf<Int>()
        for (i in 0 until machine.instructions.size) {
            val instruction = machine.instructions[i]
            val state = machine.saveState()
            when(instruction.first) {
                "nop" -> {
                    machine.instructions[i] = Pair("jmp", instruction.second)
                }
                "jmp" -> {
                    machine.instructions[i] = Pair("nop", instruction.second)
                }
            }
            if (doesRunToCompletion(machine)) {
                println("Line $i: $instruction -> ${machine.instructions[i]}")
                return machine.acc
            } else {
                machine.loadState(state)
                machine.instructions[i] = instruction
            }
        }
        return -1
    }
}