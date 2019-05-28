package com.ericho.itunes_music.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ericho.itunes_music.data.datasource.MusicDataSource
import com.ericho.itunes_music.data.local.MusicLocal
import com.ericho.itunes_music.data.remote.MusicRemote
import com.ericho.itunes_music.model.MusicInfo
import java.lang.Exception

class MusicRepositoryImpl(val remoteDataSource: MusicRemote, val local: MusicLocal) : MusicRepository {

    override fun getMusicList(searchString: String, callBack: MusicDataSource.LoadMusicCallback) {
        remoteDataSource.getMusicList(searchString, callBack)
    }

    override fun getMusicList2(searchString: String): LiveData<List<MusicInfo>> {
        val data = liveData {
            try {
                emitSource(local.getMusicList2(searchString))
                val apiResult = remoteDataSource.getMusicList2(searchString).await()
                emit(apiResult.results)
            } catch (e: Exception) {
                emit(listOf())
            }
        }
        return data
    }
}