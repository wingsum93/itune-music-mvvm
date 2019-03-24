package com.ericho.itunes_music.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ericho.itunes_music.R
import com.ericho.itunes_music.databinding.ActivitySplashBinding
import com.ericho.itunes_music.ui.mainpage2.SearchAct
import com.ericho.itunes_music.ui.welcome.WelcomeViewModel
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    val viewModel: WelcomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        viewModel.doSomeThing()
        startActivity(Intent(this, SearchAct::class.java))
        finish()

        viewModel.loginCommand.observe(this,
            Observer {
                if (it) {
                    startActivity(Intent(this, SearchAct::class.java))
                }
            })
    }


}
