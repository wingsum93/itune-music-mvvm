package com.ericho.itunes_music.ui.mainpage2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericho.itunes_music.R
import com.ericho.itunes_music.databinding.ItemMusic2Binding
import com.ericho.itunes_music.model.MusicInfo


class MainPage2Adapter(var context: Context) : RecyclerView.Adapter<MainPage2Adapter.ViewHolder>() {

    private var listener: OnMusicSelectListener? = null
    private var musicInfos: ArrayList<MusicInfo> = arrayListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MainPage2Adapter.ViewHolder {
        val binding =
            DataBindingUtil.inflate<ItemMusic2Binding>(
                LayoutInflater.from(context),
                R.layout.item_music2,
                viewGroup,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return musicInfos.size
    }

    override fun onBindViewHolder(holder: MainPage2Adapter.ViewHolder, pos: Int) {
        val info = musicInfos[pos]

        holder.bind(info)
        holder.itemView.setOnClickListener { view ->
            listener?.onMusicSelect(info)
        }
    }

    fun setData(musicInfoList: ArrayList<MusicInfo>) {
        musicInfos.apply {
            clear()
            addAll(musicInfoList)
            notifyDataSetChanged()
        }
    }

    fun setListener(listener: OnMusicSelectListener) {
        this.listener = listener
    }

    class ViewHolder(private val binding: ItemMusic2Binding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: MusicInfo) {
            binding.item = item
            binding.executePendingBindings()
        }

    }

    interface OnMusicSelectListener {
        fun onMusicSelect(musicInfo: MusicInfo)
    }
}