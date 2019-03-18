package com.ericho.itunes_music.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Created by steve_000 on 18/3/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music.utils
 */

fun <T> MutableLiveData<T>.get() = value

fun MutableLiveData<Boolean>.get() = value ?: false

fun <T> MutableLiveData<T>.set(value: T?) = setValue(value)

// ---------------  for non null live Data --------------------------------------
fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
    val mediator: NonNullMediatorLiveData<T> = NonNullMediatorLiveData()
    mediator.addSource(this, Observer { it?.let { mediator.value = it } })
    return mediator
}

fun <T> NonNullMediatorLiveData<T>.observe(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, androidx.lifecycle.Observer {
        it?.let(observer)
    })
}