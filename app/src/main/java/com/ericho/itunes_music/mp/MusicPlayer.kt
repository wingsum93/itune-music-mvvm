package com.ericho.itunes_music.mp

import android.media.AudioManager
import android.media.MediaPlayer
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * Created by steve_000 on 7/3/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music.mp
 */
class MusicPlayer : MediaPlayer(), CoroutineScope {

    private var callback: MusicCallback? = null

    private val job = SupervisorJob()
    private var dispatchJob: Job? = null

    private var durationCopy: Int = 0;


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        setAudioStreamType(AudioManager.STREAM_MUSIC)
    }

    fun loadAndPlay(url: String, afterStartRunnable: Runnable? = null, loadCompleteRunnable: Runnable? = null) {

        launch(Dispatchers.Main) {
            dispatchJob?.cancel()
            //stop and reset the music if any
//            stop()
            reset()
            // set new data source
//            delay(200)
            setDataSource(url)
            withContext(Dispatchers.IO) {
                prepare()
                durationCopy = duration
            }
            loadCompleteRunnable?.run()
            start()
            afterStartRunnable?.run()
            initializeUpdate()
        }


    }

    fun registerUpdate(callback: MusicCallback) {

        this.callback = callback
    }

    private fun initializeUpdate() {
        dispatchJob = launch(Dispatchers.IO) {
            while (true) {
                if (!isPlaying) continue
                //get music progress
                val progressInSecond = currentPosition / 1000.0
                val max = durationCopy

                val percentage = currentPosition.toFloat() / durationCopy * 100
                Timber.v("progress ------ $percentage %  , position $currentPosition, $durationCopy")
                withContext(Dispatchers.Main) {
                    callback?.onPlayingProgress(currentPosition, durationCopy)
//                    callback?.onPlayingProgress(currentPosition,duration)
                }
                delay(100)
            }
        }
    }

    interface MusicCallback {

        fun onPlayingProgress(currentPosition: Int, max: Int)
    }

    override fun stop() {
        dispatchJob?.cancel()
        super.stop()
    }
    override fun pause() {
//        this.job.cancelChildren()
        super.pause()
    }


    override fun start() {
        super.start()

    }

    override fun release() {
        super.release()
        this.coroutineContext.cancelChildren()
    }
}