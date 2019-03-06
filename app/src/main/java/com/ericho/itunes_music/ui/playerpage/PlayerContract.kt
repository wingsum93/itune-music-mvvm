package com.ericho.itunes_music.ui.playerpage

import com.ericho.itunes_music.mvp.BasePresenter
import com.ericho.itunes_music.mvp.BaseView

interface PlayerContract{
    interface View : BaseView<Presenter> {
        fun onPageIconChange(id: Int)
        fun onSeekBarChange(progress: Int)
        fun runOnMainThread(runnable: Runnable)
        fun resetSeekBar()
        fun setSeekBarMax(max: Int)
    }

    interface Presenter: BasePresenter {
        fun playMusic(progress: Int)
        fun pauseMusic()
        fun resumeMusic()
        fun stopMusic()
        fun setDataSource(url: String)
        fun toggle()
        fun destroy()
    }
}