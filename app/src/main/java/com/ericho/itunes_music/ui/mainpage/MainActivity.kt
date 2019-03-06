package com.ericho.itunes_music.ui.mainpage

import android.app.ProgressDialog

import android.os.Bundle

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ericho.itunes_music.Injection
import com.ericho.itunes_music.R

import com.ericho.itunes_music.model.MusicInfo
import java.util.ArrayList

class MainActivity : AppCompatActivity(), SearchMusicContract.View {
    // view
    var sv : SearchView? = null
    var rv_music: RecyclerView? = null
    var rl_no_network: RelativeLayout? = null

    var musicInfos : ArrayList<MusicInfo> = arrayListOf()
    var mLayoutManager: LinearLayoutManager?= null
    var adapter: MainPageAdapter? = null
    var mProgressDialog : ProgressDialog? =null

    @JvmField
    var presenter: SearchMusicContract.Presenter? = null

    override fun showLoadingDialog() {
        mProgressDialog!!.show()
    }

    override fun hideLoadingDialog() {
        mProgressDialog!!.dismiss()
    }

    override fun showNoNetwork() {
        rv_music!!.visibility = View.INVISIBLE
        sv!!.visibility = View.INVISIBLE
        rl_no_network!!.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainPagePresnter(this, this, Injection.createMusicRepo())
        initView()
        init()
    }

    override fun onMusicRunData(infos: List<MusicInfo>) {
        updateView(infos)
    }

    override fun onMusicFailData(e: Throwable) {
        Toast.makeText(this,e.message, Toast.LENGTH_SHORT ).show()
        Log.d("OnFail", e.message)
    }

    override fun setPresenter(presenter: SearchMusicContract.Presenter) {
        this.presenter = presenter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.action_refresh -> {
                finish()
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun initView(){
        sv = findViewById(R.id.sv) as SearchView
        rv_music = findViewById(R.id.rv_music) as RecyclerView
        rl_no_network = findViewById(R.id.rl_no_network) as RelativeLayout
    }

    fun init(){
        mLayoutManager = LinearLayoutManager(this)
        rv_music!!.visibility = View.VISIBLE
        rv_music!!.setLayoutManager(mLayoutManager)
        rv_music!!.addItemDecoration(DividerItemDecoration(rv_music!!.context, mLayoutManager!!.orientation))
        adapter = MainPageAdapter(musicInfos, this)
        rv_music!!.adapter = adapter

        mProgressDialog = ProgressDialog(this);
        mProgressDialog!!.setCancelable(false)
        mProgressDialog!!.setMessage(getString(R.string.loading))
        mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)

        sv!!.visibility = View.VISIBLE
        sv!!.setIconifiedByDefault(false)
        sv!!.queryHint = resources.getString(R.string.search_view_hint)
        sv!!.isFocusable = false
        sv!!.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                presenter?.getMusicList(p0!!)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if(p0.equals("")){
                    presenter?.getMusicList("avicii")
                }
                return false
            }
        })

        presenter?.getMusicList("avicii")
    }

    fun updateView(infos: List<MusicInfo>){
        musicInfos.clear()
        musicInfos.addAll(infos)
        adapter!!.notifyDataSetChanged()
    }

}
