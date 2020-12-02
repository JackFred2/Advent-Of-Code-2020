class Two : Problem {
    override fun partOne(): Int {
        val input = Utils.loadFile("inputs/day2.txt")?.lines() ?: return 0
        var count = 0
        val regex = Regex("(\\d+)-(\\d+) (\\S): ([\\S]+)\$")
        for (line in input) {
            val matches = regex.find(line)?.groupValues ?: continue
            val min = matches[1].toInt()
            val max = matches[2].toInt()
            val char = matches[3]
            val pass = matches[4]
            val charCount = Regex(char).findAll(pass).count()
            if (charCount in min..max) count++
        }
        return count
    }

    override fun partTwo(): Int {
        val input = Utils.loadFile("inputs/day2.txt")?.lines() ?: return 0
        var count = 0
        val regex = Regex("(\\d+)-(\\d+) (\\S): ([\\S]+)\$")
        for (line in input) {
            val matches = regex.find(line)?.groupValues ?: continue
            val pos1 = matches[1].toInt()
            val pos2 = matches[2].toInt()
            val char = matches[3]
            val pass = matches[4]
            val pos1HasChar = if (pos1 <= pass.length) pass[pos1 - 1] == char[0] else false
            val pos2HasChar = if (pos2 <= pass.length) pass[pos2 - 1] == char[0] else false
            if (pos1HasChar != pos2HasChar) count++
        }
        return count
    }
}