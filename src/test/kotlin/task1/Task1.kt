package task1

import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.RepeatedTest
import task1.solution.Solution
import java.util.SortedSet
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

const val MAX_NUMBER_OF_NAMES = 3
const val MAX_NAME_LENGTH = 3
const val N_REPEAT = 100

class Task1 {

    private fun getRandomWord(size: Int, alphabet: CharRange = ('a'..'z'))
    = List(size) { alphabet.random() }.joinToString("")

    @RepeatedTest(N_REPEAT)
    fun correctSort() {
        val names = List((1..MAX_NUMBER_OF_NAMES).random()) {
            getRandomWord((1..MAX_NAME_LENGTH).random())
        }.toSortedSet()

        val alphabet = ('a'..'z').zip(('a'..'z').shuffled()).toMap()

        val codedNames = (names.map { name ->
            name.map {
                alphabet.getOrDefault(it, ' ')
            }.joinToString("")
        })

        val result = Solution.solve(codedNames)
        assertNotNull(result)

        val newAlphabet = result.toList().zip(('a'..'z')).toMap()

        val decodedNames = (codedNames.map { name ->
            name.map {
                newAlphabet.getOrDefault(it, ' ')
            }.joinToString("")
        })

        assertEquals(decodedNames, decodedNames.sorted())
    }

    @RepeatedTest(N_REPEAT)
    fun notCorrectSort() {
        var names: SortedSet<String> = sortedSetOf<String>()
        while(names.size <= 1) {
            names = List((2..MAX_NUMBER_OF_NAMES).random()) {
                getRandomWord((1..MAX_NAME_LENGTH).random(), 'd'..'z')
            }.toSortedSet()
        }
        val newNames = names.map { "a${it}" }.toMutableList()
        newNames.addAll(names.map { "b${it}" }.reversed())
        assertNull(Solution.solve(newNames))
    }
}