package com.ericho.itunes_music.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ericho.itunes_music.R

/**
 * Created by steve_000 on 21/5/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music.ui
 */
class AdVideoPlayerActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_video)

        toVideoFragment()
    }

    private fun toVideoFragment() {

        val videoUrl = "http://japan-def.s3.ap-northeast-1.amazonaws.com/sample.mkv"
        val videoUrl2 = "http://tedious.s3.us-west-1.amazonaws.com/SampleVideoBae_1280x720_1mb.mp4"


        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout,VideoFragment.newInstance(videoUrl2))
            .commit()
    }
}