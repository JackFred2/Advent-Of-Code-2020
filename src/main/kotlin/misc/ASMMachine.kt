package misc

class ASMMachine(lines: List<String>) {
    var acc: Int = 0
    var cursor: Int = 0

    val instructions: MutableList<Pair<String, Int>> = mutableListOf()

    init {
        val regex = Regex("(\\w{3}) ([+|-]\\d+)")
        for (line in lines) {
            val match = regex.find(line) ?: continue
            instructions.add(Pair(match.groupValues[1], match.groupValues[2].toInt()))
        }
    }

    fun saveState(): Pair<Int, Int> {
        return Pair(acc, cursor)
    }

    fun loadState(state: Pair<Int, Int>) {
        this.acc = state.first
        this.cursor = state.second
    }

    fun cycle(): Boolean {
        if (stopped()) return false
        val instruction = instructions[cursor]
        when (instruction.first) {
            "nop" -> cursor++
            "acc" -> {
                cursor++
                acc += instruction.second
            }
            "jmp" -> cursor += instruction.second
            else -> {
                println("Unsupported instruction $instruction, ignoring.")
                cursor++
                return false
            }
        }
        return true
    }

    fun stopped(): Boolean {
        return !(0 until instructions.size).contains(cursor)
    }

    fun stoppedProperly(): Boolean {
        return cursor == instructions.size
    }
}