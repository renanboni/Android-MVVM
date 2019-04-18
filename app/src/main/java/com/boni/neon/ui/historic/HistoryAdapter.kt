package com.boni.neon.ui.historic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boni.neon.R
import com.boni.neon.entities.ContactView
import com.boni.neon.ext.toMonetary
import kotlinx.android.synthetic.main.transfer_historic_item_list.view.*

class HistoryAdapter constructor(private val contacts: MutableList<ContactView>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transfer_historic_item_list, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = contacts.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.contact_name
        private val phone = itemView.phone
        private val value = itemView.value
        private val avatar = itemView.avatar

        fun bind(contactView: ContactView) {
            name.text = contactView.name
            phone.text = contactView.phone
            value.text = contactView.value.toMonetary()

            avatar.setName(contactView.name)
            avatar.setImage(contactView.avatar)
        }
    }
}