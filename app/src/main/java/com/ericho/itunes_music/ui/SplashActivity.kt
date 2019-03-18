package com.ericho.itunes_music.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ericho.itunes_music.R
import com.ericho.itunes_music.ui.mainpage2.SearchAct

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
    }

    private fun initView() {
        startActivity(Intent(this, SearchAct::class.java))
        finish()
    }



}
