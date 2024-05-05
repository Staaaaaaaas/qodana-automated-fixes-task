package task2

import task2.solution.Solution

fun main() {
    val inputs = List(5) { readln() }
//    val inputs = listOf(
//        "src/main/resources/task2/example1/file1.json",
//        "src/main/resources/task2/example1/file2.json",
//        "src/main/resources/task2/example1/file3.json",
//        "src/main/resources/task2/example1/file4.json",
//        "src/main/resources/task2/example1/file5.json",
//    )
    Solution.solve(inputs)
}