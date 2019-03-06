package com.ericho.itunes_music.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ericho.itunes_music.R
import com.ericho.itunes_music.ui.mainpage2.SearchAct
import me.wangyuwei.particleview.ParticleView

class SplashActivity : AppCompatActivity() {

    lateinit var pView: ParticleView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
        init()
    }

    fun initView(){
        pView = findViewById<ParticleView>(R.id.pView)
    }

    fun init(){
        pView.let {
            it.startAnim()
            it.setOnParticleAnimListener(ParticleView.ParticleAnimListener {
//                startActivity(Intent(this, MainActivity::class.java))
                startActivity(Intent(this, SearchAct::class.java))
                finish()
            })
        }
    }


}
