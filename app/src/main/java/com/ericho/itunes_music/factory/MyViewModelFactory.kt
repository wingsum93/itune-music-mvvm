package com.ericho.itunes_music.factory

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ericho.itunes_music.Injection
import com.ericho.itunes_music.data.repository.MusicRepository
import com.ericho.itunes_music.ui.mainpage2.HomePageViewModel

/**
 * Created by steve_000 on 6/3/2019.
 * for project Itunes_music_demo
 * package name com.ericho.itunes_music.factory
 */
class MyViewModelFactory private constructor(
    private val app: Application,
    private val musicRepo: MusicRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(HomePageViewModel::class.java) ->
                HomePageViewModel(app, musicRepo)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T


    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: MyViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(MyViewModelFactory::class.java) {
                INSTANCE ?: MyViewModelFactory(
                    application,
                    Injection.createMusicRepo()
                )
                    .also { INSTANCE = it }
            }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}