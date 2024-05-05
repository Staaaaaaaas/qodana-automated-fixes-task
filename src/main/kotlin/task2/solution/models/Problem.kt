package task2.solution.models

import kotlinx.serialization.Serializable

@Serializable
data class Problem(
    val hash: String,
    val data: Set<String>
)