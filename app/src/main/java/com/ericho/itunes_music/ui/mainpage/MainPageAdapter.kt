package com.ericho.itunes_music.ui.mainpage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ericho.itunes_music.R
import com.ericho.itunes_music.model.MusicInfo
import com.ericho.itunes_music.ui.playerpage.PlayerActivity
import com.ericho.itunes_music.utils.Key
import com.ericho.itunes_music.utils.NetworkHelper
import com.github.siyamed.shapeimageview.CircularImageView

class MainPageAdapter(var musicInfos: List<MusicInfo>, var context: Context): RecyclerView.Adapter<MainPageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainPageAdapter.ViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.item_music, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return musicInfos.size
    }

    override fun onBindViewHolder(p0: MainPageAdapter.ViewHolder, p1: Int) {
        val info = musicInfos[p1]
        p0.txt_artist.text = info.artist
        p0.txt_song.text = info.music
        Glide.with(context).load(info.icon_large).into(p0.img_icon)

        p0.itemView.setOnClickListener{view ->
            if(NetworkHelper.isNetworkAvailable(context)){
                var i: Intent = Intent(context, PlayerActivity::class.java)
                i.putExtra(Key.INTENT_MUSIC_INFO, info)
                context.startActivity(i)
            }else{
                Toast.makeText(context, context.getString(R.string.reconnect_network), Toast.LENGTH_SHORT).show()
            }

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var txt_artist = itemView.findViewById(R.id.txt_artist) as TextView
        var txt_song = itemView.findViewById(R.id.txt_song) as TextView
        var img_icon = itemView.findViewById(R.id.img_icon) as CircularImageView
    }
}