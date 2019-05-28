package com.ericho.itunes_music.http_client

import com.ericho.itunes_music.model.MusicInfo
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {
    @GET("/search")
    fun search(@Query("term") term:String = "Avicii",
               @Query("entity") entity:String = "song",
               @Query("country") country:String = "US",
               @Query("media") media:String = "music",
               @Query("limit") limit:Int = 15

    ): Call<ApiResult<MusicInfo>>

    @GET("/search")
    fun search2(
        @Query("term") term: String = "Avicii",
        @Query("entity") entity: String = "song",
        @Query("country") country: String = "US",
        @Query("media") media: String = "music",
        @Query("limit") limit: Int = 20

    ): Deferred<ApiResult<MusicInfo>>
}