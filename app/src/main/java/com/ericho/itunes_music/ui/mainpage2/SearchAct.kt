package com.ericho.itunes_music.ui.mainpage2

import android.app.SearchManager
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ericho.itunes_music.R
import com.ericho.itunes_music.databinding.ActivitySearchBinding
import com.ericho.itunes_music.model.MusicInfo
import com.google.gson.Gson
import com.roger.catloadinglibrary.CatLoadingView
import org.koin.android.ext.android.inject
import timber.log.Timber

/**
 * Created by steve_000 on 6/3/2019.
 * for project Itunes_music_demo
 * package name com.ericho.itunes_music.ui.mainpage2
 */
class SearchAct : AppCompatActivity(), SearchView.OnQueryTextListener {

    val viewModel: HomePageViewModel by inject()

    lateinit var no_network_layout: RelativeLayout
    lateinit var sv: SearchView

    val catView: CatLoadingView by lazy { getCatLoadingView() }


    val gson = Gson()

    private lateinit var adapter: MainPage2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySearchBinding>(this, R.layout.activity_search)
//        viewModel = obtainViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initView(binding)

        viewModel.getMusicList("abc")

    }

    private fun initView(binding: ActivitySearchBinding?) {
        if (binding == null) return

        adapter = MainPage2Adapter(this)
        sv = binding.sv
        binding.sv.setOnQueryTextListener(this)
        val searchManager = getSystemService(SearchManager::class.java)
        binding.sv.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        no_network_layout = findViewById(R.id.rl_no_network)
        binding.rvMusic.layoutManager = LinearLayoutManager(this)
        binding.rvMusic.adapter = adapter

        //set listener
        adapter.setListener(object : MainPage2Adapter.OnMusicSelectListener {
            override fun onMusicSelect(musicInfo: MusicInfo) {
                Timber.d(gson.toJson(musicInfo))
                Timber.d(musicInfo.artistDisplayString)
                viewModel.clickToPlayMusic(musicInfo)
            }
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                catView.show(supportFragmentManager, "")
            } else {
                catView.dismiss()
            }
        })

        viewModel.playMusicRawSource.observe(this, Observer {

        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Timber.d("click search $query")
        query?.let {
            viewModel!!.getMusicList(it)

            val im = getSystemService(InputMethodManager::class.java)
            im.hideSoftInputFromWindow(this.currentFocus.windowToken, 0)
            sv.clearFocus()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun getCatLoadingView(): CatLoadingView {
        return CatLoadingView().apply { isCancelable = false }
    }

    override fun onStop() {
        viewModel.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}