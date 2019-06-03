package com.ericho.itunes_music.widget.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

class CustomBehavior<V : View> : CoordinatorLayout.Behavior<V> {

    constructor() : super() {
        //Used when the layout has a behavior attached via the Annotation;

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        //Used when the layout has a behavior attached via xml (Within the xml file e.g.
        //<app:layout_behavior=".link.to.your.behavior">

    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: V, dependency: View): Boolean {

        return super.layoutDependsOn(parent, child, dependency)
    }
}