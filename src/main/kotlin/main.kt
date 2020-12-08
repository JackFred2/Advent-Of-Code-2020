import problems.*

val days = mapOf(
        "1" to One(),
        "2" to Two(),
        "3" to Three(),
        "4" to Four(),
        "5" to Five(),
        "6" to Six(),
        "7" to Seven(),
        "8" to Eight()
)

fun main(args: Array<String>) {
    println("Advent of code 2020")
    var selected = if (args.isEmpty()) "" else args[0]
    while (!days.containsKey(selected)) {
        println("Valid days: ${days.keys}")
        print("Select a day: ")
        selected = readLine().toString().toLowerCase()
    }
    val problem = days[selected] ?: error("")
    print("Part 1: ")
    println(problem.partOne())
    print("Part 2: ")
    println(problem.partTwo())
}