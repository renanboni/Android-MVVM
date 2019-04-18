package com.boni.neon.ui.sendmoney

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boni.neon.R
import com.boni.neon.entities.ContactView
import kotlinx.android.synthetic.main.contact_item_list.view.*

class ContactsAdapter constructor(
    private val contacts: MutableList<ContactView>
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
        with(contacts[position]) {
            holder.phone.text = phone
            holder.name.text = name
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.contact_name
        val phone: TextView = itemView.phone
    }
}