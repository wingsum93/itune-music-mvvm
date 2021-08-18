package com.ericho.itunes_music.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
@BindingAdapter("app:musicData", requireAll = true)
fun RecyclerView.bindMusicData(items: ArrayList<MusicInfo>) {
    (adapter as? MainPage2Adapter)?.setData(items)
}

@BindingAdapter("app:imageUrl")
fun ImageView.bindImageUrl(url: String?) {
    if (url == null) return
    Glide.with(this.context).load(url).into(this)
}

@BindingAdapter("app:imageUrl")
fun ImageView.bindImageUrl(url: Int?) {
    if (url == null) return
    Glide.with(this.context).load(url).into(this)
}