package com.ericho.itunes_music.ui.mainpage2

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
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
    val loading = MutableLiveData<Boolean>()

    //for error message
    val errorMessageEvent:ObservableField<String> = ObservableField()
    val showErrorMessage:ObservableBoolean = ObservableBoolean(false)


    //use to indicate playing which music
    val playMusicRawSource:MutableLiveData<String> = MutableLiveData()
    val playing:MutableLiveData<Boolean> = MutableLiveData()
    val timeline:MutableLiveData<Double> = MutableLiveData()

    private val gson = Gson()

    val handler:Handler = Handler(Looper.getMainLooper())

    fun getMusicList(searchData: String){

        loading.postValue(true)
        Timber.i("downloading music for $searchData")
        musicRepository.getMusicList(searchData,object : MusicDataSource.LoadMusicCallback{
            override fun onLoadSuccess(musics: List<MusicInfo>) {
                loading.postValue(false)
                musicList.apply {
                    clear()
                    addAll(musics)
                }
                Timber.w(gson.toJson(musics))
            }

            override fun onLoadError(e: Throwable) {
                loading.postValue(false)
                musicList.apply {
                    clear()
                }
                errorMessageEvent.set(e.message)
                showErrorMessage.set(true)
                handler.postDelayed(Runnable { showErrorMessage.set(false)  },5000)
                Timber.e("API return ${errorMessageEvent.get()}")
            }
        })
    }

    fun clickToPlayMusic(musicUrl:String){
        if (musicUrl == playMusicRawSource.value){
            return
        }
        _playMusic(musicUrl)
    }

    private fun _playMusic(musicUrl: String){
        playMusicRawSource.value = musicUrl
        timeline.value = 0.toDouble()
        playing.value = true

    }
}