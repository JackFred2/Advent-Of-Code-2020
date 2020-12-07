package problems

class Seven : Problem {
    private fun checkForContainsTarget(target: String, map: Map<String, Set<String>>): Set<String> {
        val containsSet = mutableSetOf<String>()
        for (canContain in map.getOrDefault(target, setOf())) {
            containsSet.add(canContain)
            containsSet.addAll(checkForContainsTarget(canContain, map))
        }
        return containsSet
    }

    private fun howManyInside(target: String, containsMap: MutableMap<String, MutableMap<String, Int>>): Int {
        return howManyInside(target, containsMap, mutableMapOf())
    }

    private fun howManyInside(target: String, containsMap: MutableMap<String, MutableMap<String, Int>>, cache: MutableMap<String, Int>): Int {
        var count = 0
        if (cache.contains(target)) {
            return cache[target]!!
        } else {
            val targetContains = containsMap[target]?.keys ?: setOf()
            for (colour in targetContains) {
                count += containsMap[target]?.get(colour)?.times(1 + howManyInside(colour, containsMap, cache)) ?: 0
            }
            cache[target] = count
            return count
        }
    }

    override fun partOne(): Int {
        val input = Utils.loadFile("inputs/day7.txt")?.lines()?.filter { it.isNotEmpty() } ?: return -1
        val containedBy = mutableMapOf<String, MutableSet<String>>()
        val initialRegex = Regex("([^\\n]+) bags contain ([^.]++)")
        val splitRegex = Regex("(\\d+) (\\w+( \\w+)?)")
        for (line in input) {
            val match = initialRegex.find(line) ?: continue
            val ruleFor = match.groupValues[1]
            if (match.groupValues[2] != "no other bags") {
                for (contained in splitRegex.findAll(match.groupValues[2])) {
                    containedBy.putIfAbsent(contained.groupValues[2], mutableSetOf())
                    containedBy[contained.groupValues[2]]?.add(ruleFor)
                }
            }
        }

        val containsTarget = checkForContainsTarget("shiny gold", containedBy)
        return containsTarget.size
    }

    override fun partTwo(): Int {
        val input = Utils.loadFile("inputs/day7.txt")?.lines()?.filter { it.isNotEmpty() } ?: return -1
        val containsMap = mutableMapOf<String, MutableMap<String, Int>>()
        val initialRegex = Regex("([^\\n]+) bags contain ([^.]++)")
        val splitRegex = Regex("(\\d+) (\\w+( \\w+)?)")
        for (line in input) {
            val match = initialRegex.find(line) ?: continue
            val thisContains = mutableMapOf<String, Int>()
            if (match.groupValues[2] != "no other bags") {
                for (contains in splitRegex.findAll(match.groupValues[2])) {
                    thisContains[contains.groupValues[2]] = contains.groupValues[1].toInt()
                }
            }
            containsMap[match.groupValues[1]] = thisContains
        }

        return howManyInside("shiny gold", containsMap)
    }
}