package com.example.contacts.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.ModelClass.ContactModel
import com.example.contacts.R

class ContactsAdapter(var context: Context, var contactsList : ArrayList<ContactModel>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_contacts, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_cont_name.text = contactsList[position].name
        Log.e("tv_cont_name:::",""+contactsList[position].name)
        holder.tv_cont_number.text = contactsList[position].number

    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_cont_name: TextView
        var tv_cont_number: TextView

        init {
            tv_cont_name = view.findViewById(R.id.tv_cont_name)
            tv_cont_number = view.findViewById(R.id.tv_cont_number)
        }
    }

}