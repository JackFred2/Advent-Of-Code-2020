package problems

import misc.Utils

class Four : Problem {
    private val needed = listOf("pid","hgt","byr","ecl","iyr","eyr","hcl")
    private val neededTwo = mapOf(
            "byr" to { s: String -> IntRange(1920, 2002).contains(s.toInt()) },
            "iyr" to { s: String -> IntRange(2010, 2020).contains(s.toInt()) },
            "eyr" to { s: String -> IntRange(2020, 2030).contains(s.toInt()) },
            "hcl" to { s: String -> Regex("#[0-9a-f]{6}").matches(s) },
            "ecl" to { s: String -> listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(s) },
            "pid" to { s: String -> Regex("[0-9]{9}").matches(s) },
            "hgt" to { s: String -> {
                val cmMatch = Regex("(\\d+)cm").find(s)
                val inMatch = Regex("(\\d+)in").find(s)
                when {
                    cmMatch != null -> {
                        IntRange(150, 193).contains(cmMatch.groupValues[1].toInt())
                    }
                    inMatch != null -> {
                        IntRange(59, 76).contains(inMatch.groupValues[1].toInt())
                    }
                    else -> false
                }
            }.invoke()}
    )

    private fun checkValid(properties: Map<String, String>, deepSearch: Boolean): Boolean {
        needed.forEach {
            if (properties.containsKey(it)) {
                if (deepSearch) {
                    val result = neededTwo[it]?.invoke(properties[it]?: "") ?: return false
                    if (!result) return false
                }
            } else {
                return false
            }
        }
        return true
    }

    override fun partOne(): Int {
        val propertyRegex = "([a-z]{3}):(\\S+)".toRegex()
        val lines = Utils.loadFile("inputs/day4.txt")?.lines() ?: return 0

        var validCount = 0
        val propertyMap = HashMap<String, String>()
        for (line in lines) {
            if (line.isEmpty()) {
                if (checkValid(propertyMap, false)) {
                    validCount++
                }
                propertyMap.clear()
            } else {
                for (match in propertyRegex.findAll(line)) {
                    propertyMap[match.groupValues[1]] = match.groupValues[2]
                }
            }
        }
        return validCount
    }

    override fun partTwo(): Int {
        val propertyRegex = "([a-z]{3}):(\\S+)".toRegex()
        val lines = Utils.loadFile("inputs/day4.txt")?.lines() ?: return 0

        var validCount = 0
        val propertyMap = HashMap<String, String>()
        for (line in lines) {
            if (line.isEmpty()) {
                if (checkValid(propertyMap, true)) {
                    validCount++
                }
                propertyMap.clear()
            } else {
                for (match in propertyRegex.findAll(line)) {
                    propertyMap[match.groupValues[1]] = match.groupValues[2]
                }
            }
        }
        return validCount
    }
}