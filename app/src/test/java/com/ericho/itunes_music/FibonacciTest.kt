package com.ericho.itunes_music

import org.junit.Test


class FibonacciTest {

    @Test
    fun xxx() {

        val testInput = arrayOf(1, 22, 9, 12, 14, 376, 378, 317812, 317811, 317810)
            .toIntArray()
        nextFibonacci(testInput)
    }

    fun nextFibonacci(input: IntArray) {
        val max = input.maxOrNull() ?: 0
        val fibonacciList = buildSequence(max).toList()

        println("Output:")
        for (value: Int in input) {
            // find the next fibonacci value for each number in array
            var a = 0
            for ((index, fibonacciValue) in fibonacciList.withIndex()) {
                val isNextFibonacciNumber = value < fibonacciValue
                if (isNextFibonacciNumber) {
                    a = index
                    break
                }
            }
            println(fibonacciList[a])
        }

    }

    private fun buildSequence(number: Int): Sequence<Int> {
        return sequence {
            var terms = Pair(1, 1)

            while (true) {
                yield(terms.first)
                terms = Pair(terms.second, terms.first + terms.second)

                if (terms.first > number) {
                    yield(terms.first)
                    break
                }
            }
        }
    }
}