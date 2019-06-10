package com.ericho.itunes_music.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MusicInfo(
    @SerializedName("artistName")
    val artist: String,
    @SerializedName("trackName")
    val music: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("artworkUrl100")
    val icon_large: String,
    @SerializedName("artworkUrl30")
    val icon: String
) : Serializable {

    val artistDisplayString
        get() = "Artist Name: $artist"
    val musicDisplayString
        get() = "Song Name: $music"
}