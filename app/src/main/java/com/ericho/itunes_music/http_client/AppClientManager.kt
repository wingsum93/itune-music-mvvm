package com.ericho.itunes_music.http_client

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient



class AppClientManager {
    private var okHttpClient: OkHttpClient
    private var retrofit: Retrofit
    init {
        okHttpClient = OkHttpClient()
        retrofit = Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    companion object {
        private val manager = AppClientManager()

        fun getClient(): Retrofit {
            return manager.retrofit
        }
    }

}