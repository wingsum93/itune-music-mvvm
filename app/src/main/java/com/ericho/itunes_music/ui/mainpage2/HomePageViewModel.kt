package com.ericho.itunes_music.ui.mainpage2

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.ericho.itunes_music.SingleLiveEvent
import com.ericho.itunes_music.data.datasource.MusicDataSource
import com.ericho.itunes_music.data.repository.MusicRepository
import com.ericho.itunes_music.model.MusicInfo
import com.google.gson.Gson
import timber.log.Timber

/**

 */
class HomePageViewModel(
    private val c:Application,
    private val musicRepository: MusicRepository) : AndroidViewModel(c) {


    var musicList:  ObservableArrayList<MusicInfo> = ObservableArrayList()
    val empty = ObservableBoolean(false)
    val loading = ObservableBoolean(false)

    val errorMessageEvent:SingleLiveEvent<String> = SingleLiveEvent()

    private val gson = Gson()

    fun getMusicList(searchData: String){

        loading.set(true)
        Timber.i("downloading music for $searchData")
        musicRepository.getMusicList(searchData,object : MusicDataSource.LoadMusicCallback{
            override fun onLoadSuccess(musics: List<MusicInfo>) {
                loading.set(false)
                musicList.apply {
                    clear()
                    addAll(musics)
                }
                Timber.w(gson.toJson(musics))
            }

            override fun onLoadError(e: Throwable) {
                loading.set(false)
                musicList.apply {
                    clear()
                }
                errorMessageEvent.value = e.message
            }
        })
    }

    private fun displayErrorMessage(){

    }

}