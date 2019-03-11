package com.ericho.itunes_music.ui.mainpage2

import android.app.Application
import android.widget.SeekBar
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ericho.itunes_music.data.datasource.MusicDataSource
import com.ericho.itunes_music.data.repository.MusicRepository
import com.ericho.itunes_music.model.MusicInfo
import com.ericho.itunes_music.mp.MusicPlayer
import com.ericho.itunes_music.utils.toMusicDisplayFormat
import com.google.gson.Gson
import kotlinx.coroutines.*
import timber.log.Timber

/**

 */
class HomePageViewModel(
    private val c: Application,
    private val musicRepository: MusicRepository
) : AndroidViewModel(c) {

    val pageScope: CoroutineScope = CoroutineScope(Dispatchers.Main) + SupervisorJob()
    var musicList: ObservableArrayList<MusicInfo> = ObservableArrayList()
    val empty = ObservableBoolean(false)
    val loading = MutableLiveData<Boolean>()

    //for error message
    val errorMessageEvent: ObservableField<String> = ObservableField()
    val showErrorMessage: ObservableBoolean = ObservableBoolean(false)


    //use to indicate playing which music
    val playMusicRawSource: MutableLiveData<String> = MutableLiveData() //
    // indicating now is playing music even the music is suspend for dragging
    val playing: ObservableBoolean = ObservableBoolean(false)
    val playSongName: ObservableField<String> = ObservableField()
    val seekbarProgress: ObservableInt = ObservableInt(0)
    val isDragging = ObservableBoolean(false)
    val showPlayerConsole = ObservableBoolean(false)


    //other
    private val gson = Gson()
    //    val mediaPlayer: MediaPlayer by getMediaPlayer()
    val musicPlayer: MusicPlayer by lazy { MusicPlayer() }


    fun getMusicList(searchData: String) {

        loading.postValue(true)
        Timber.i("downloading music for $searchData")
        musicRepository.getMusicList(searchData, object : MusicDataSource.LoadMusicCallback {
            override fun onLoadSuccess(musics: List<MusicInfo>) {
                loading.postValue(false)
                musicList.apply {
                    clear()
                    addAll(musics)
                }
            }

            override fun onLoadError(e: Throwable) {
                loading.postValue(false)
                musicList.apply {
                    clear()
                }
                errorMessageEvent.set(e.message)
                showErrorMessage.set(true)
                pageScope.launch {
                    delay(5000)
                    showErrorMessage.set(false)
                }
                Timber.e("API return ${errorMessageEvent.get()}")
            }
        })
    }

    fun clickToPlayMusic(musicInfo: MusicInfo) {
        if (musicInfo.previewUrl == playMusicRawSource.value) {
            return
        }
        playSongName.set(musicInfo.musicDisplayString)
        _playMusic(musicInfo.previewUrl)
    }

    private fun _playMusic(musicUrl: String) {
        playMusicRawSource.value = musicUrl
        showPlayerConsole.set(true)
        playing.set(false)

        musicPlayer.loadAndPlay(musicUrl, Runnable { playing.set(true) })
        musicPlayer.registerUpdate(object : MusicPlayer.MusicCallback {
            override fun onPlayingProgress(currentPosition: Int, max: Int) {
                seekbarProgress.set(currentPosition / 1000)
            }
        })
        musicPlayer.setOnCompletionListener {
            playing.set(false)
        }
    }

    fun clickButton1() {
        Timber.w("Click bbbt111")
        if (playing.get()) {
            musicPlayer.pause()
            playing.set(false)
        } else {
            playing.set(true)
            musicPlayer.start()
        }
    }

    fun closeMusicPlayerAndView() {
        Timber.d("closeMusicPlayerAndView 1")
        musicPlayer.apply {
            stop()
            playMusicRawSource.value = ""
            seekbarProgress.set(0)
            showPlayerConsole.set(false)
        }
    }

    fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        Timber.d("onProgressChanged1 $progress , $fromUser")
        if (fromUser) {
            // do reset music player
            musicPlayer.apply {
                seekTo(progress * 1000)
            }

        }
    }

    fun onStartTrackingTouch(seekBar: SeekBar) {
        isDragging.set(true)
        musicPlayer.pause()

    }

    fun onStopTrackingTouch(seekBar: SeekBar) {
        isDragging.set(false)
        if (playing.get()) {
            musicPlayer.start()
        }
    }

    fun getTimeDisplayString(time: Int): String {
        return time.toMusicDisplayFormat()
    }

    override fun onCleared() {
        pageScope.coroutineContext.cancelChildren()
        musicPlayer.release()
        super.onCleared()
    }

}