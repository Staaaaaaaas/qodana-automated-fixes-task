package task1.solution

object Solution {

    private fun normalizeNames(names: Collection<String>): Collection<String> {
        val maxLength = names.maxOfOrNull { it.length } ?: 0
        return names.map { it + " ".repeat(maxLength - it.length) }
    }

    fun solve(names: Collection<String>): String? {
        val graph = HashMap<Char, MutableList<Char>>()
        val inDegree = HashMap<Char, Int>()

        normalizeNames(names).fold(" ") { acc, name ->
            acc.zip(name).first { (char1, char2) -> char1 != char2 }.let {
                graph.getOrPut(it.first) { mutableListOf() }.add(it.second)
                inDegree[it.second] = inDegree[it.second]?.inc() ?: 1
            }
            name
        }
        val queue = ArrayDeque<Char>()
        if (inDegree.getOrDefault(' ', 0) != 0) return null
        queue.add(' ')
        ('a'..'z').forEach { if (inDegree.getOrDefault(it, 0) == 0) queue.add(it) }
        val result = mutableListOf<Char>()
        while (queue.isNotEmpty()) {
            val popped = queue.removeLast()
            graph[popped]?.forEach {
                inDegree[it] = inDegree[it]?.dec() ?: 0
                if (inDegree[it] == 0) queue.add(it)
            }
            if (popped != ' ') result.add(popped)
        }
        return if (result.toSet() != ('a'..'z').toSet()) null
        else result.joinToString("")
    }
}