package com.boni.neon.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boni.neon.R
import com.boni.neon.entities.ContactView
import com.boni.neon.ui.widgets.CircularImageView
import kotlinx.android.synthetic.main.contact_item_list.view.*

class ContactsAdapter constructor(
    private val contacts: MutableList<ContactView>,
    private val clickListener: (ContactView) -> Unit
) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item_list, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = contacts.count()

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(contacts[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: CircularImageView = itemView.avatar
        private val name: TextView = itemView.contact_name
        private val phone: TextView = itemView.phone

        fun bind(contactView: ContactView, clickListener: (ContactView) -> Unit) {
            name.text = contactView.name
            phone.text = contactView.phone

            avatar.setName(contactView.name)
            avatar.setImage(contactView.avatar)
            itemView.setOnClickListener { clickListener(contactView) }
        }
    }
}