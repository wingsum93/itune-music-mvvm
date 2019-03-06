package com.ericho.itunes_music.ui.playerpage


import android.os.Bundle
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatSeekBar
import com.ericho.itunes_music.R
import com.ericho.itunes_music.model.MusicInfo
import com.ericho.itunes_music.utils.Key


class PlayerActivity : AppCompatActivity(), PlayerContract.View{
    // view
    var txtArtist: TextView? = null
    var txtSound: TextView? = null
    var seekBarHint: TextView? = null
    var imgbtn_playPause: AppCompatImageButton? = null
    var sb: AppCompatSeekBar? = null

    var musicInfo : MusicInfo? = null

    @JvmField
    var presenter: PlayerContract.Presenter? = null

    override fun runOnMainThread(runnable: Runnable) {
        runOnUiThread(runnable)
    }

    override fun setSeekBarMax(max: Int) {
        sb!!.max = max
    }

    override fun resetSeekBar() {
        sb!!.progress = 0
        seekBarHint!!.text = "0:00"
    }

    override fun onPageIconChange(id: Int) {
        imgbtn_playPause!!.setImageResource(id)
    }

    override fun onSeekBarChange(progress: Int) {
        var time = Math.ceil(progress.toDouble() / 1000f).toInt()
        if (time < 10)
           seekBarHint!!.setText("0:0" + time)
        else
           seekBarHint!!.setText("0:" + time)

        sb!!.progress = progress
    }

    override fun setPresenter(presenter: PlayerContract.Presenter) {
        this.presenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound)
        initView()
        init()
    }

    fun initView(){
        txtSound = findViewById(R.id.txtSound) as TextView
        txtArtist = findViewById(R.id.txtArtist) as TextView
        seekBarHint = findViewById(R.id.txtSeekBarHint) as TextView
        sb = findViewById(R.id.sb) as AppCompatSeekBar
        imgbtn_playPause = findViewById(R.id.imgbtn_playPause) as AppCompatImageButton
    }

    fun init(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        PlayerPresenter(this, this)

        // retrieve data
        musicInfo = intent.getSerializableExtra(Key.INTENT_MUSIC_INFO) as MusicInfo
        if(musicInfo != null){
            txtSound!!.text = musicInfo!!.music
            txtArtist!!.text = musicInfo!!.artist

            // init mediaplayer
            presenter!!.setDataSource(musicInfo!!.url)
        }

        imgbtn_playPause!!.setOnClickListener { view ->
            presenter!!.toggle()
        }

        sb!!.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromTouch: Boolean) {}

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {
                presenter!!.playMusic(p0!!.progress)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        presenter!!.pauseMusic()
    }

    override fun onDestroy() {
        presenter!!.destroy()
        super.onDestroy()
    }
}
