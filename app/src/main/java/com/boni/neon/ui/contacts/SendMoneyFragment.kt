package com.boni.neon.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.boni.neon.NeonApplication
import com.boni.neon.R
import com.boni.neon.entities.ContactView
import com.boni.neon.ui.sendmoney.SendMoneyDialogFragment
import kotlinx.android.synthetic.main.fragment_sendmoney.*
import javax.inject.Inject

class SendMoneyFragment: Fragment() {

    private lateinit var sendMoneyViewModel: SendMoneyViewModel

    @Inject
    lateinit var factory: SendMoneyVMFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_sendmoney,
        container,
        false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as NeonApplication).createSendMoneyComponent()?.inject(this)
        sendMoneyViewModel = ViewModelProviders.of(this, factory).get(SendMoneyViewModel::class.java)

        sendMoneyViewModel.getContacts()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sendMoneyViewModel.viewState.observe(this, Observer {
            it?.let { handleState(it) }
        })
    }

    private fun handleState(state: SendMoneyViewState) {
        state.contacts?.let {
            contacts.adapter = ContactsAdapter(it, this::onContactClicked)

            val drawable = ContextCompat.getDrawable(context!!, R.drawable.contacts_separator)
            contacts.addItemDecoration(ContactListItemDecorator(drawable!!))
        }
    }

    private fun onContactClicked(contactView: ContactView) {
        /*
            Unfortunately isn't possible to do it using the navigation arch
         */
        fragmentManager?.let {
            SendMoneyDialogFragment.newInstance(contactView).show(
                it,
                SendMoneyDialogFragment.TAG
            )
        }
    }
}