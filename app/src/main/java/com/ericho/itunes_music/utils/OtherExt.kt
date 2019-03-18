package com.ericho.itunes_music.utils

import androidx.lifecycle.MutableLiveData

/**
 * Created by steve_000 on 18/3/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music.utils
 */

fun <T> MutableLiveData<T>.get() = value

fun <T> MutableLiveData<T>.set(value: T?) = setValue(value)