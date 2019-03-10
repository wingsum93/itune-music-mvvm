package com.ericho.itunes_music.utils

/**
 * Created by steve_000 on 10/3/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music.utils
 */

fun Int.toMusicDisplayFormat(): String {

    val hh = this / 60
    val reminder = this % 60
    val mm: String = when (reminder) {
        in 0..9 -> "0$reminder"
        else -> reminder.toString()
    }
    return "$hh:$mm"

}