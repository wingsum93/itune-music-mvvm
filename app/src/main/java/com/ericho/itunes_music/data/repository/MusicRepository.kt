package com.ericho.itunes_music.data.repository

import androidx.lifecycle.LiveData
import com.ericho.itunes_music.data.datasource.MusicDataSource
import com.ericho.itunes_music.model.MusicInfo

/**
 * Created by steve_000 on 19/3/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music.data.repository
 */
interface MusicRepository {
    fun getMusicList(searchString: String, callBack: MusicDataSource.LoadMusicCallback)

    fun getMusicList2(searchString: String): LiveData<List<MusicInfo>>
}