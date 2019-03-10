package com.ericho.itunes_music

import org.junit.Test

/**
 * Created by steve_000 on 9/3/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music
 */
class DividerTest {


    @Test
    fun xxx() {
        val a = 29967.toFloat()
        val b = 29977

        println(a / b * 100)
    }

    @Test
    fun aa12() {

        for (test in 0..70) {
            val s = toSixString(test)
            println(s)
        }

    }

    private fun toSixString(int: Int): String {
        val hours = int / 60
        val min = int % 60
        val mm: String = when (min) {
            in 0..9 -> "0$min"
            else -> min.toString()
        }
        return "$hours:$mm"
    }
}