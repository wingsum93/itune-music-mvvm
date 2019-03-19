package com.ericho.itunes_music.data.repository

import com.ericho.itunes_music.data.datasource.MusicDataSource
import com.ericho.itunes_music.data.remote.MusicRemote

class MusicRepositoryImpl(val remoteDataSource: MusicRemote) : MusicRepository {

    override fun getMusicList(searchString: String, callBack: MusicDataSource.LoadMusicCallback) {
        remoteDataSource.getMusicList(searchString, callBack)
    }

}