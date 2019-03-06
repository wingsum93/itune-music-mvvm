package com.ericho.itunes_music

import com.ericho.itunes_music.data.remote.MusicRemote
import com.ericho.itunes_music.data.repository.MusicRepository

object Injection {
    fun createMusicRepo(): MusicRepository{
        return MusicRepository(MusicRemote())
    }
}