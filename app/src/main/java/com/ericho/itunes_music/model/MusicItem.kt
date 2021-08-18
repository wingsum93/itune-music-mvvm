package com.ericho.itunes_music.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = MusicItem.TABLE_NAME)
data class MusicItem(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = COLUMN_ARTIST_NAME)
    var artist: String?,
    @ColumnInfo(name = COLUMN_TRACK_NAME)
    var music: String?,
    @ColumnInfo(name = COLUMN_PREVIEW_URL)
    var previewUrl: String?,
    @ColumnInfo(name = COLUMN_ICON_LARGE)
    var icon_large: String?,
    @ColumnInfo(name = COLUMN_ICON)
    var icon: String?
) : Serializable {
    companion object {
        const val TABLE_NAME = "music_item"
        const val COLUMN_ID = "id"
        const val COLUMN_ARTIST_NAME = "artistName"
        const val COLUMN_TRACK_NAME = "trackName"
        const val COLUMN_PREVIEW_URL = "previewUrl"
        const val COLUMN_ICON_LARGE = "artworkUrl100"
        const val COLUMN_ICON = "artworkUrl30"
    }
}