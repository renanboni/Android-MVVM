package com.boni.neon.ui.sendmoney

import android.animation.Animator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.boni.neon.NeonApplication
import com.boni.neon.R
import com.boni.neon.entities.ContactView
import com.boni.neon.ext.*
import kotlinx.android.synthetic.main.send_money_dialog.*
import javax.inject.Inject

class SendMoneyDialogFragment: DialogFragment() {

    private lateinit var sendMoneyViewModel: SendMoneyViewModel

    @Inject
    lateinit var factory: SendMoneyVMFactory

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as NeonApplication).createSendMoneySubComponent()?.inject(this)
        sendMoneyViewModel = ViewModelProviders.of(this, factory).get(SendMoneyViewModel::class.java)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        sendMoneyViewModel.viewState.observe(this, Observer {
            it?.let { handleState(it) }
        })

        sendMoneyViewModel.error.observe(this, Observer {
            it?.let {
                activity?.showMessage(it.localizedMessage)
            }
        })

        arguments?.let {
            with(it.getSerializable(CONTACT) as ContactView) {
                nameTxt.text = name
                phoneTxt.text = phone
                avatarImg.setImage(avatar)
                avatarImg.setName(name)

                sendMoneyViewModel.clientId = id
            }
        }

        close.setOnClickListener { dismiss() }
        send.setOnClickListener { sendMoneyViewModel.sendMoney() }

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
                    sendMoneyViewModel.value = parsed
                    val formatted = parsed.toMonetary()

                    current = formatted
                    valueTxt.setText(formatted)
                    valueTxt.setSelection(current.length)

                    valueTxt.addTextChangedListener(this)
                }
            }
        })

        setupAnimation()
    }

    private fun handleState(state: SendMoneyState) {
        if(state.isLoading) {
            progress.show()
            send.disable()
        } else {
            progress.hide()
            send.enable()
        }

        if(state.moneySent) {
            fireworks.show()
            fireworks.playAnimation()
            send.disable()
        }
    }

    override fun onResume() {
        super.onResume()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun setupAnimation() {
        fireworks.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator) {
                activity?.showMessage(getString(R.string.succesful_transfer_msg))
                dismiss()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        fireworks?.removeAllAnimatorListeners()
    }
}