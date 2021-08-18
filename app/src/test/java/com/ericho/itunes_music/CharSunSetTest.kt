package com.ericho.itunes_music

import org.junit.Test

class CharSubSetTest {


    @Test
    fun xxx() {
        val case1 = isSubset(arrayOf('A', 'B', 'C', 'D', 'E'), arrayOf('A', 'D', 'E'))
        val case2 = isSubset(arrayOf('A', 'B', 'C', 'D', 'E'), arrayOf('A', 'D', 'Z'))
        val case3 = isSubset(arrayOf('A', 'D', 'E'), arrayOf('A', 'A', 'D', 'E'))

        val case4 = isSubset(arrayOf('A', 'D', 'E'), arrayOf('A', 'A', 'A'))

        println("case 1 - $case1")
        println("case 2 - $case2")
        println("case 3 - $case3")
        println("case 4 - $case4")
    }

    fun isSubset(firstArray: Array<Char>, secondArray: Array<Char>): Boolean {
        // make a set for 1st and 2nd array
        val firstSet = firstArray.toSet()
        val secondSet = secondArray.toSet()

        //check first set has all second set's char
        for (char: Char in secondSet) {
            // start check
            if (!firstSet.contains(char)) {
                return false
            }
        }
        return true
    }
}