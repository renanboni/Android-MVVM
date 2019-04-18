package com.boni.neon.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.boni.neon.NeonApplication
import com.boni.neon.R
import com.boni.neon.entities.ContactView
import com.boni.neon.ext.hide
import com.boni.neon.ext.show
import com.boni.neon.ui.sendmoney.SendMoneyDialogFragment
import kotlinx.android.synthetic.main.fragment_contacts.*
import javax.inject.Inject

class ContactsFragment: Fragment() {

    private lateinit var contactsVieModel: ContactsVieModel

    @Inject
    lateinit var factory: ContactsVMFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_contacts,
        container,
        false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as NeonApplication).createSendMoneyComponent()?.inject(this)
        contactsVieModel = ViewModelProviders.of(this, factory).get(ContactsVieModel::class.java)

        contactsVieModel.getContacts()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        contactsVieModel.viewState.observe(this, Observer {
            it?.let { handleState(it) }
        })
    }

    private fun handleState(state: ContactsViewState) {
        state.contacts?.let {
            contacts.adapter = ContactsAdapter(it, this::onContactClicked)

            val drawable = ContextCompat.getDrawable(context!!, R.drawable.contacts_separator)
            contacts.addItemDecoration(ContactListItemDecorator(drawable!!))
        }

        if(state.isLoading) {
            progress.show()
        } else {
            progress.hide()
        }
    }

    private fun onContactClicked(contactView: ContactView) {
        /*
            Unfortunately there isn't a proper way to do it using the navigation arch
         */
        fragmentManager?.let {
            SendMoneyDialogFragment.newInstance(contactView).show(
                it,
                SendMoneyDialogFragment.TAG
            )
        }
    }
}