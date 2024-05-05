package task1

import task1.solution.Solution

fun main() {
    val n = readln().toInt()
    val names = mutableListOf<String>()
    repeat(n) {
        names.add(readln())
    }
    println(Solution.solve(names) ?: "Impossible")
}