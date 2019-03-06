package com.ericho.itunes_music.data.datasource

import com.ericho.itunes_music.model.MusicInfo

interface MusicDataSource {
    interface LoadMusicCallback {
        fun onLoadSuccess(musics: List<MusicInfo>)
        fun onLoadError(e: Throwable)
    }

    fun getMusicList(searchString: String, callBack: LoadMusicCallback)
}