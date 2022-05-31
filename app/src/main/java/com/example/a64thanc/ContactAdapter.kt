package com.example.a64thanc

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact.view.*

class ContactAdapter(private val contacts: MutableList<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curcontact: Contact = contacts[position]
        Log.d(ContentValues.TAG,"${curcontact.name} => ${curcontact.phone}")
        holder.itemView.apply {
            tv_cntname1.text =  curcontact.name
            tv_cntpos.text =   curcontact.position
            tv_cnt1.text =curcontact.phone.toString()
            }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

}