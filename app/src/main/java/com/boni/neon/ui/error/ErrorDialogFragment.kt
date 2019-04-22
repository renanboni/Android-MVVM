package com.boni.neon.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.error_dialog_layout.*

class ErrorDialogFragment: DialogFragment() {

    companion object {
        const val TAG = "ErrorDialogFragment"
        private const val ERROR_MESSAGE = "errorMessage"
        private lateinit var tryAgain: () -> Unit

        fun newInstance(click: () -> Unit, msg: String? = null): ErrorDialogFragment {
            val fragment = ErrorDialogFragment()
            this.tryAgain = click

            val bundle = Bundle()
            bundle.putString(ERROR_MESSAGE, msg)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            com.boni.neon.R.layout.error_dialog_layout,
            container,
            false
        )
    }

    override fun onResume() {
        super.onResume()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animation.playAnimation()
        tryAgainBtn.setOnClickListener {
            tryAgain()
            dismiss()
        }
        closeBtn.setOnClickListener { dismiss() }

        arguments?.let {
            errorMessage.text = it.getString(ERROR_MESSAGE, "")
        }
    }
}