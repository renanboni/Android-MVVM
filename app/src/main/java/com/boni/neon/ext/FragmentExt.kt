package com.boni.neon.ext

import androidx.fragment.app.Fragment
import com.boni.neon.ui.error.ErrorDialogFragment

fun Fragment.showErrorMessage(action: () -> Unit, msg: String? = null) {
    fragmentManager?.let {
        ErrorDialogFragment.newInstance(action, msg).show(it, ErrorDialogFragment.TAG)
    }
}