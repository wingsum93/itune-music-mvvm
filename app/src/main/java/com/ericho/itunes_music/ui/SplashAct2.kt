package com.ericho.itunes_music.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ericho.itunes_music.R
import me.itangqi.waveloadingview.WaveLoadingView

/**
 * Created by steve_000 on 7/3/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music.ui
 */
class SplashAct2 :AppCompatActivity() {

    lateinit var loadingView:WaveLoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)

        loadingView =  findViewById(R.id.waveLoadingView)

        loadingView.startAnimation()
    }
}