package com.ericho.itunes_music.ui.playerpage

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import com.ericho.itunes_music.R


class PlayerPresenter(var context: Context, var view: PlayerContract.View) : PlayerContract.Presenter{
    var mp: MediaPlayer = MediaPlayer()
    var isPlaying :Boolean = false
    val totalDuration: Int = 30000
    var currentProgress = 0
    val mHandler = Handler()
    var runable: Runnable = object : Runnable {
        override fun run() {
            if (mp != null) {
                currentProgress = mp!!.currentPosition
                var flag : Boolean = (currentProgress < 30000-500)
                Log.d("----------------: ", ""+currentProgress)

                if(flag){
                    view.onSeekBarChange(currentProgress)
                }else{
                    stopMusic()
                }
            }
            mHandler.postDelayed(this, 300)
        }
    }

    override fun destroy() {
        mHandler.removeCallbacks(runable)
    }

    override fun toggle() {
        if(isPlaying){
            pauseMusic()
        }else{
            resumeMusic()
        }
    }

    override fun setDataSource(url: String) {
        mp.setDataSource(url)
        mp.prepare()
        view.resetSeekBar()
        view.setSeekBarMax(totalDuration)
    }

    override fun stopMusic() {
        isPlaying = false
        mp!!.pause()
        currentProgress = 0
        mp.seekTo(0)
        view.resetSeekBar()
        view.onPageIconChange(R.drawable.ic_play_circle_outline_black)
    }

    override fun playMusic(progress: Int) {
        isPlaying = true
        currentProgress = progress
        mp!!.seekTo(currentProgress)
        mp!!.start()
        view.onPageIconChange(R.drawable.ic_pause_circle_outline_black)
    }

    override fun pauseMusic() {
        isPlaying = false
        mp!!.pause()
        view.onPageIconChange(R.drawable.ic_play_circle_outline_black)
    }

    override fun resumeMusic() {
        isPlaying = true
        mp!!.start()
        view.onPageIconChange(R.drawable.ic_pause_circle_outline_black)
    }

    init{
        view.setPresenter(this)
        view.runOnMainThread(runable)
    }

}