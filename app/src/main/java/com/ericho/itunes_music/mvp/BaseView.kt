package com.ericho.itunes_music.mvp

interface BaseView<in T> {
    fun setPresenter(presenter: T)
}