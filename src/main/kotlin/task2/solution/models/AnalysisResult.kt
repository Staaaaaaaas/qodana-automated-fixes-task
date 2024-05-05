package task2.solution.models

import kotlinx.serialization.Serializable

@Serializable
data class AnalysisResult(
    val problems: List<Problem>
)

