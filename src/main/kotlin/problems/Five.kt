package problems

import misc.Utils

class Five : Problem {
    private fun getValue(bitString: String): Int {
        var value = 0
        for (i in 1..bitString.length) {
            if (bitString[i - 1] == '1') {
                value += pow(2, bitString.length - i)
            }
        }
        return value
    }

    private fun pow(a: Int, b: Int): Int {
        assert(b >= 0)
        if (a == 0) return 0
        if (b == 0) return 1
        var c = 1
        for (i in 1..b) c *= a
        return c
    }

    override fun partOne(): Int {
        val input = Utils.loadFile("inputs/day5.txt")?.lines() ?: return -1
        var maxSeat = 0
        for (line in input) {
            if (line.isNotEmpty()) {
                val front = line.substring(0..6).replace('F', '0').replace('B', '1')
                val side = line.substring(7..9).replace('L', '0').replace('R', '1')
                val seatId = getValue(front) * 8 + getValue(side)
                maxSeat = maxOf(seatId, maxSeat)
            }
        }
        return maxSeat
    }

    override fun partTwo(): Int {
        val input = Utils.loadFile("inputs/day5.txt")?.lines() ?: return -1
        val seats = HashSet<Int>()
        for (line in input) {
            if (line.isNotEmpty()) {
                val front = line.substring(0..6).replace('F', '0').replace('B', '1')
                val side = line.substring(7..9).replace('L', '0').replace('R', '1')
                val seatId = getValue(front) * 8 + getValue(side)
                seats.add(seatId)
            }
        }
        val min = seats.minOrNull() ?: return -1
        val max = seats.maxOrNull() ?: return -1
        for (i in min..max) {
            if (!seats.contains(i)) return i
        }
        return -1
    }
}