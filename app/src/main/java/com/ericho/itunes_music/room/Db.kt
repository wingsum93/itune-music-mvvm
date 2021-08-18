package com.ericho.itunes_music.room

import android.content.Context
import androidx.room.Room

object Db {
    private var instance: AppDatabase? = null
    fun getInstance(context: Context): AppDatabase {
        if (instance == null) {
            synchronized(Db::class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        AppDatabase.DATABASE_NAME
                    ).build()
                }
            }
        }
        return instance!!
    }
}