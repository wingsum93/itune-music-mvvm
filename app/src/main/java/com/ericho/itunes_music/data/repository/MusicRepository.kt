package com.ericho.itunes_music.data.repository

import com.ericho.itunes_music.data.datasource.MusicDataSource

class MusicRepository(val remoteDataSource: MusicDataSource): MusicDataSource{
    override fun getMusicList(searchString: String, callBack: MusicDataSource.LoadMusicCallback) {
        remoteDataSource.getMusicList(searchString, callBack)
    }

}