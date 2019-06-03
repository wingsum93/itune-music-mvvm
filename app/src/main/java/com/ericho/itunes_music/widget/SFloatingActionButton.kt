package com.ericho.itunes_music.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.ericho.itunes_music.widget.behavior.CustomBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SFloatingActionButton : FloatingActionButton, CoordinatorLayout.AttachedBehavior {

    constructor(c: Context) : super(c)

    constructor(c: Context, attrs: AttributeSet) : super(c, attrs)

    constructor(c: Context, attrs: AttributeSet, defStyleAttr: Int) : super(c, attrs, defStyleAttr)

    override fun getBehavior(): CoordinatorLayout.Behavior<*> {
        return CustomBehavior<View>()
    }
}