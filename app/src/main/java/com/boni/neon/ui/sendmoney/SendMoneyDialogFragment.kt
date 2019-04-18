package com.boni.neon.ui.sendmoney

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.boni.neon.R
import com.boni.neon.entities.ContactView
import kotlinx.android.synthetic.main.send_money_dialog.*


class SendMoneyDialogFragment: DialogFragment() {

    companion object {
        const val TAG = "SendMoneyDialogFragment"
        private const val CONTACT = "contact"

        fun newInstance(contactView: ContactView): SendMoneyDialogFragment {
            return SendMoneyDialogFragment().apply {
                this.arguments = Bundle().apply {
                    this.putSerializable(CONTACT, contactView)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.send_money_dialog,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            with(it.getSerializable(CONTACT) as ContactView) {
                nameTxt.text = name
                phoneTxt.text = phone
            }
        }

        close.setOnClickListener { dismiss() }
    }

    override fun onResume() {
        super.onResume()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}