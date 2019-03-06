package com.ericho.itunes_music.ui.mainpage

import com.ericho.itunes_music.model.MusicInfo
import com.ericho.itunes_music.mvp.BasePresenter
import com.ericho.itunes_music.mvp.BaseView

interface SearchMusicContract {
    interface View : BaseView<Presenter>{
        fun onMusicRunData(info : List<MusicInfo>)
        fun onMusicFailData(e: Throwable)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showNoNetwork()
    }

    interface Presenter : BasePresenter{
        fun getMusicList(searchData: String)
    }
}