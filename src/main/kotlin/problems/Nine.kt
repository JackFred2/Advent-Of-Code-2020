package problems

import misc.Utils
import java.lang.Long.max
import java.lang.Long.min

class Nine : Problem {
    private val size = 25

    private fun getList(): List<Long>? {
        return Utils.loadFile("inputs/day9.txt")?.lines()
            ?.filter { s -> s.isNotBlank() }
            ?.map { s -> s.toLong() }
            ?: return null
    }

    override fun partOne(): Long {
        var index = size
        val nums = getList() ?: return -1
        while (index < nums.size) {
            val subList = nums.subList(index - size, index)
            if (!validValue(subList, nums[index])) return nums[index]
            index++
        }
        return -1
    }

    private fun validValue(subList: List<Long>, candidate: Long): Boolean {
        for (a in 0 until (subList.size - 1))
            for (b in (a + 1) until subList.size)
                if (subList[a] + subList[b] == candidate) return true
        return false
    }

    override fun partTwo(): Number {
        val target = partOne()
        val nums = getList() ?: return -1
        for (i in nums.indices) {
            var sum = 0L
            var j = i
            var min = Long.MAX_VALUE
            var max = Long.MIN_VALUE
            while (sum < target) {
                sum += nums[j]
                min = min(min, nums[j])
                max = max(max, nums[j])
                if (sum == target) {
                    println("Indexes from $i to $j.")
                    return min + max
                }
                j++
            }
        }
        return -1
    }
}