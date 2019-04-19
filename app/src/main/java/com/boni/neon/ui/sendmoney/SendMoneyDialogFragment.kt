package com.boni.neon.ui.sendmoney

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.boni.neon.entities.ContactView
import com.boni.neon.ext.toMonetary
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

    private var current = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            com.boni.neon.R.layout.send_money_dialog,
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
                avatarImg.setImage(avatar)
                avatarImg.setName(name)
            }
        }

        close.setOnClickListener { dismiss() }

        valueTxt.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (s.toString() != current) {
                    valueTxt.removeTextChangedListener(this)

                    val cleanString = s.toString().replace("[R$,.]".toRegex(), "")

                    val parsed = cleanString.toFloat()
                    val formatted = parsed.toMonetary()

                    current = formatted
                    valueTxt.setText(formatted)
                    valueTxt.setSelection(current.length)

                    valueTxt.addTextChangedListener(this)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}