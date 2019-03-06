package com.ericho.itunes_music.ui.mainpage2

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ericho.itunes_music.R
import com.ericho.itunes_music.databinding.ActivitySearchBinding
import com.ericho.itunes_music.factory.MyViewModelFactory

/**
 * Created by steve_000 on 6/3/2019.
 * for project Itunes_music_demo
 * package name com.ericho.itunes_music.ui.mainpage2
 */
class SearchAct :AppCompatActivity() {

    var viewModel : HomePageViewModel? = null

    lateinit var no_network_layout:RelativeLayout

    val handler = Handler()

    private lateinit var adapter:MainPage2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySearchBinding>(this,R.layout.activity_search)
        viewModel = obtainViewModel()
        binding.viewModel = viewModel
        adapter = MainPage2Adapter(this)
        binding
        no_network_layout = findViewById(R.id.rl_no_network)

        viewModel!!.errorMessageEvent.observe(this, Observer {
            no_network_layout.visibility = View.VISIBLE
            val runnerable = object :Runnable{
                override fun run() {
                    no_network_layout.visibility = View.GONE
                }
            }
            handler.postDelayed(runnerable,2000)
        })


    }


    fun obtainViewModel():HomePageViewModel = ViewModelProviders.of(this,MyViewModelFactory.getInstance(application))
        .get(HomePageViewModel::class.java)
}