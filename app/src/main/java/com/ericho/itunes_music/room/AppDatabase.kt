package com.ericho.itunes_music.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ericho.itunes_music.model.MusicItem

@Database(entities = [MusicItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        val DATABASE_NAME = "music.db"
    }

    abstract fun itemDao(): MusicDao

}