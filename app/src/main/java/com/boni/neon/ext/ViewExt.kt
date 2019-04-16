package com.boni.neon.ext

import android.view.View
import android.view.View.*

fun View.hide() {
    if(visibility == VISIBLE) {
        visibility = GONE
    }
}

fun View.show() {
    if(visibility == GONE || visibility == INVISIBLE) {
        visibility = VISIBLE
    }
}