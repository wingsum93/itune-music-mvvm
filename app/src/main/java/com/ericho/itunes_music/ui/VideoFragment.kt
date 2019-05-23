package com.ericho.itunes_music.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ericho.itunes_music.R
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView

import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

import com.google.android.exoplayer2.util.Util


/**
 * Created by steve_000 on 21/5/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music.ui
 */
class VideoFragment : Fragment() {


    var playerView:PlayerView? = null


    lateinit var videoUrl: String


    companion object {
        private const val KEY_VIDEO_URL = "KEY_VIDEO_URL"
        fun newInstance(videoUrl: String): VideoFragment {
            val fragment = VideoFragment()
            val b = Bundle()
            b.putString(KEY_VIDEO_URL, videoUrl)
            fragment.arguments = b
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = arguments
        videoUrl = b!!.getString(KEY_VIDEO_URL, "")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val player = ExoPlayerFactory.newSimpleInstance(context)
        player.playWhenReady = true
        playerView = view.findViewById<PlayerView>(R.id.playerView)

        playerView?.player = player as Player

        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, "yourApplicationName")
        )
        // This is the MediaSource representing the media to be played.
        val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(videoUrl))
        // Prepare the player with the source.
        player.prepare(videoSource)


        player.addListener(object :Player.EventListener{
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                playbackState == Player.STATE_READY
            }
        })
    }


}