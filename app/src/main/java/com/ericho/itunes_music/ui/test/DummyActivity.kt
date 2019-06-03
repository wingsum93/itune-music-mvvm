package com.ericho.itunes_music.ui.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ericho.itunes_music.R
import com.ericho.itunes_music.databinding.ActivityDummyBinding
import com.ericho.itunes_music.databinding.ActivitySearchBinding
import com.ericho.itunes_music.ui.mainpage2.HomePageViewModel
import com.ericho.itunes_music.ui.mainpage2.MainPage2Adapter
import org.koin.android.ext.android.inject

class DummyActivity : AppCompatActivity() {

    val viewModel: HomePageViewModel by inject()
    lateinit var adapter: MainPage2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)
        val binding = DataBindingUtil.setContentView<ActivityDummyBinding>(this, R.layout.activity_dummy)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initView(binding)
        viewModel.getMusicList("abc")

    }

    private fun initView(binding: ActivityDummyBinding?) {
        binding?.let {
            adapter = MainPage2Adapter(this)
            it.rv.layoutManager = LinearLayoutManager(this)
            it.rv.adapter = adapter
        }
    }

}