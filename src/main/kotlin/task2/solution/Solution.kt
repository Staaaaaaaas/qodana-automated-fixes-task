package task2.solution

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import task2.solution.models.AnalysisResult
import java.io.File

object Solution {
    fun solve(args: List<String>) {
        val files = args.map { File(it) }.toMutableList()
        require(files.take(2).all {it.exists()})
        val analysisFirst = Json.decodeFromString<AnalysisResult>(files.first().readText())
        files.removeFirst()
        val analysisSecond = Json.decodeFromString<AnalysisResult>(files.first().readText())
        files.removeFirst()
        val result = listOf(
            analysisFirst.problems.filter { it !in analysisSecond.problems },
            analysisSecond.problems.filter { it !in analysisFirst.problems },
            analysisFirst.problems.filter { it in analysisSecond.problems }
        )
        val prettyJson = Json {
            prettyPrint = true
            prettyPrintIndent = " "
        }
        result.zip(files).forEach {
            it.second.printWriter().use {out ->
                out.println(
                    prettyJson.encodeToString(
                        AnalysisResult(it.first)
                    )
                )
            }
        }
    }
}