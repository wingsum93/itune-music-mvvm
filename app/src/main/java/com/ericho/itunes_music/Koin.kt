package com.ericho.itunes_music

import com.ericho.itunes_music.data.remote.MusicRemote
import com.ericho.itunes_music.data.repository.MusicRepository
import com.ericho.itunes_music.data.repository.MusicRepositoryImpl
import com.ericho.itunes_music.ui.mainpage2.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by steve_000 on 19/3/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music
 */

val appModule = module {
    single<MusicRepository> { MusicRepositoryImpl(get()) }
    viewModel { HomePageViewModel(get(), get()) }
    factory { MusicRemote() }

}