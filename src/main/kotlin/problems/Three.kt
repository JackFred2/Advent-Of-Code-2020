package problems

val TREE_CHAR = '#'

class Three : Problem {
    private fun countTrees(map: Array<BooleanArray>, xDiff: Int, yDiff: Int): Int {
        var x = 0
        var y = 0
        var count = 0
        while (y < map.size) {
            if (map[y][x]) {
                count += 1
            }
            x = (x + xDiff) % map[0].size
            y += yDiff
        }

        return count
    }

    private fun makeMap(): Array<BooleanArray> {
        val input = Utils.loadFile("inputs/day3.txt")?.lines() ?: return Array(0) { BooleanArray(0) }
        val width = input[0].length
        val height = input.size
        val map = Array(height) { BooleanArray(width) }
        for (y in 0 until height) {
            val line = input[y]
            for (x in 0 until width) {
                map[y][x] = line[x] == TREE_CHAR
            }
        }
        return map
    }

    override fun partOne(): Int {
        val map = makeMap()

        return countTrees(map, 3, 1)
    }

    override fun partTwo(): Int {
        val map = makeMap()

        return countTrees(map, 1, 1) *
                countTrees(map, 3, 1) *
                countTrees(map, 5, 1) *
                countTrees(map, 7, 1) *
                countTrees(map, 1, 2)
    }
}