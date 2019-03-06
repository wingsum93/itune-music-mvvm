package com.ericho.itunes_music.data.remote

import com.ericho.itunes_music.data.datasource.MusicDataSource
import com.ericho.itunes_music.http_client.ApiResult
import com.ericho.itunes_music.http_client.MusicApi
import com.ericho.itunes_music.http_client.AppClientManager
import com.ericho.itunes_music.model.MusicInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MusicRemote: MusicDataSource{
    private val api: MusicApi = AppClientManager.getClient().create(MusicApi::class.java)

    override fun getMusicList(searchString: String, callBack: MusicDataSource.LoadMusicCallback) {
        // call api
        val call = api.search(searchString)
        call.enqueue(object: Callback<ApiResult<MusicInfo>>{
            override fun onFailure(call: Call<ApiResult<MusicInfo>>, t: Throwable) {
               callBack.onLoadError(t)
            }

            override fun onResponse(call: Call<ApiResult<MusicInfo>>, response: Response<ApiResult<MusicInfo>>) {
                var a = response.body()
                if(a == null ){
                    callBack.onLoadError(Throwable())
                    return
                }

                val list: List<MusicInfo> = a.results
                callBack.onLoadSuccess(list)

            }
        })
    }

}