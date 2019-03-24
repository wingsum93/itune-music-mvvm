package com.ericho.itunes_music.ui.welcome

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ericho.itunes_music.R
import com.ericho.itunes_music.SingleLiveEvent
import com.ericho.itunes_music.utils.set
import timber.log.Timber

/**
 * Created by steve_000 on 24/3/2019.
 * for project itune-music-mvvm
 * package name com.ericho.itunes_music.ui.welcome
 */
class WelcomeViewModel(app: Application) : AndroidViewModel(app) {

    val bigImageUrl: MutableLiveData<Int> = MutableLiveData()
    val contentDescription: MutableLiveData<String> = MutableLiveData()

    val loginCommand: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {

        bigImageUrl.set(R.drawable.adorable_animal_breed_356378)
        contentDescription.set("A Dog.!@")
    }


    fun doSomeThing() {

    }

    fun onLoginClick(v: View) {
        Timber.i("login click")
        loginCommand.set(true)
    }
}