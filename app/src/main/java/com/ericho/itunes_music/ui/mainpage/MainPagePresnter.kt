package com.ericho.itunes_music.ui.mainpage

import android.content.Context
import com.ericho.itunes_music.data.datasource.MusicDataSource
import com.ericho.itunes_music.data.repository.MusicRepository
import com.ericho.itunes_music.model.MusicInfo
import com.ericho.itunes_music.utils.NetworkHelper

class MainPagePresnter(var context: Context, var view: SearchMusicContract.View, var repository: MusicRepository): SearchMusicContract.Presenter {
    override fun getMusicList(searchData: String) {
        if(NetworkHelper.isNetworkAvailable(context)){
            view.showLoadingDialog()
            repository.getMusicList(searchData, object :MusicDataSource.LoadMusicCallback{
                override fun onLoadSuccess(musics: List<MusicInfo>) {
                    view.onMusicRunData(musics)
                    view.hideLoadingDialog()
                }

                override fun onLoadError(e: Throwable) {
                    view.onMusicFailData(e)
                    view.hideLoadingDialog()
                }
            })
        }else{
            view.showNoNetwork()
        }
    }

    init{
        view.setPresenter(this)
    }

}