package com.ericho.itunes_music.http_client

import com.google.gson.annotations.SerializedName

class ApiResult<T>{
    @SerializedName("resultCount")
    var resultCount:Int = 0
    @SerializedName("results")
    var results:List<T> = arrayListOf()
}