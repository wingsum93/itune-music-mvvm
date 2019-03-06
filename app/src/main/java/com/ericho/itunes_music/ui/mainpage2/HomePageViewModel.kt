package com.ericho.itunes_music.ui.mainpage2

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import com.ericho.itunes_music.SingleLiveEvent
import com.ericho.itunes_music.data.datasource.MusicDataSource
import com.ericho.itunes_music.data.repository.MusicRepository
import com.ericho.itunes_music.model.MusicInfo

/**

 */
class HomePageViewModel(
    private val c:Application,
    private val musicRepository: MusicRepository) : AndroidViewModel(c) {


    var musicList:  ObservableArrayList<MusicInfo> = ObservableArrayList()
    val empty = ObservableBoolean(false)
    val loading = ObservableBoolean(false)

    val errorMessageEvent:SingleLiveEvent<String> = SingleLiveEvent()


    fun getMusicList(searchData: String){

        loading.set(true)

        musicRepository.getMusicList(searchData,object : MusicDataSource.LoadMusicCallback{
            override fun onLoadSuccess(musics: List<MusicInfo>) {
                loading.set(false)
                musicList.apply {
                    clear()
                    addAll(musics)
                }
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