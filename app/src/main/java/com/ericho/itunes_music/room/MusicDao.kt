package com.ericho.itunes_music.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ericho.itunes_music.model.MusicItem

@Dao
interface MusicDao {
    @Query("SELECT * FROM ${MusicItem.TABLE_NAME}")
    fun getAll(): List<MusicItem>

    @Query("SELECT * FROM ${MusicItem.TABLE_NAME} WHERE ${MusicItem.COLUMN_ID} IN (:musicIds)")
    fun loadAllByIds(musicIds: IntArray): List<MusicItem>

    @Query("SELECT * FROM ${MusicItem.TABLE_NAME} WHERE ${MusicItem.COLUMN_ARTIST_NAME} LIKE :artistName LIMIT 1")
    fun findByArtistName(artistName: String): MusicItem?

    @Insert
    fun insertAll(vararg musicItems: MusicItem): List<Long>

    @Delete
    fun delete(musicItem: MusicItem): Int

}