package com.ericho.itunes_music.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ericho.itunes_music.model.MusicInfo
import com.ericho.itunes_music.ui.mainpage2.MainPage2Adapter

/**
 * Created by steve_000 on 6/3/2019.
 * for project Itunes_music_demo
 * package name com.ericho.itunes_music.utils
 */

//@BindingAdapter("app:adapter")
//fun RecyclerView.bindAdapterData(adapter:MainPage2Adapter){
//    setAdapter(adapter)
//}
@BindingAdapter("app:musicData",requireAll = true)
fun RecyclerView.bindMusicData(items:ArrayList<MusicInfo>){
    (adapter as? MainPage2Adapter)?.setData(items)
}