package com.ericho.itunes_music

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

/**
 * Created by steve_000 on 6/3/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music
 */
class App :Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        Timber.plant(Timber.DebugTree())
    }
}