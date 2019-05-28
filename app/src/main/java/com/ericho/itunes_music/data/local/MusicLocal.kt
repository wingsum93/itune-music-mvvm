package com.ericho.itunes_music.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ericho.itunes_music.model.MusicInfo

class MusicLocal() {

    fun getMusicList2(searchString: String): LiveData<List<MusicInfo>> {
        return liveData {
            emit(listOf())
        }
    }
}