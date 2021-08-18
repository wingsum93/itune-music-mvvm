package com.ericho.itunes_music.ui.mainpage2

import android.app.Application
import android.widget.SeekBar
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ericho.itunes_music.data.datasource.MusicDataSource
import com.ericho.itunes_music.data.repository.MusicRepository
import com.ericho.itunes_music.model.MusicInfo
import com.ericho.itunes_music.mp.MusicPlayer
import com.ericho.itunes_music.utils.get
import com.ericho.itunes_music.utils.set
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
    val loading = MutableLiveData<Boolean>()

    //for error message
    val errorMessageEvent: MutableLiveData<String> = MutableLiveData()
    val showErrorMessage: MutableLiveData<Boolean> = MutableLiveData()


    //use to indicate playing which music
    val playMusicRawSource: MutableLiveData<String> = MutableLiveData() //
    // indicating now is playing music even the music is suspend for dragging
    val playing: MutableLiveData<Boolean> = MutableLiveData()
    val playSongName: MutableLiveData<String> = MutableLiveData()
    val playArtistName: MutableLiveData<String> = MutableLiveData()
    val seekbarProgress: MutableLiveData<Int> = MutableLiveData()
    val isDragging: MutableLiveData<Boolean> = MutableLiveData()
    val showPlayerConsole: MutableLiveData<Boolean> = MutableLiveData()
    val musicResourceLoading: MutableLiveData<Boolean> = MutableLiveData()


    //other
    private val gson = Gson()
    //    val mediaPlayer: MediaPlayer by getMediaPlayer()
    val musicPlayer: MusicPlayer by lazy { MusicPlayer() }


    init {
        seekbarProgress.set(0)
        showPlayerConsole.set(false)
        musicResourceLoading.set(false)
        isDragging.set(false)
        playing.set(false)
        showErrorMessage.set(false)
    }

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
        playArtistName.set(musicInfo.artistDisplayString)
        _playMusic(musicInfo.previewUrl)
    }

    private fun _playMusic(musicUrl: String) {
        playMusicRawSource.value = musicUrl
        showPlayerConsole.set(true)
        playing.set(false)
        musicResourceLoading.set(true)
        musicPlayer.loadAndPlay(musicUrl,
            Runnable { playing.set(true) },
            Runnable { musicResourceLoading.set(false) })
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

    fun onStop() {

    }

    fun onDestroy() {
        closeMusicPlayerAndView()
    }

    override fun onCleared() {
        pageScope.coroutineContext.cancelChildren()
        musicPlayer.release()
        super.onCleared()
    }

}