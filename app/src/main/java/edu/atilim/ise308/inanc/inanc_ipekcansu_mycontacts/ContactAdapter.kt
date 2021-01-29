package edu.atilim.ise308.inanc.inanc_ipekcansu_mycontacts

import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val mainActivity: MainActivity, var contactList: List<ContactModel>): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    inner class ContactViewHolder(contactItemView: View): RecyclerView.ViewHolder(contactItemView), View.OnClickListener{
        internal var name = contactItemView.findViewById<TextView>(R.id.tv_name)
        internal var surname = contactItemView.findViewById<TextView>(R.id.tv_surname)
        internal var phoneNumber = contactItemView.findViewById<TextView>(R.id.tv_phoneNumber)
        internal var ib_edit = contactItemView.findViewById<ImageButton>(R.id.imageButton_edit)
        internal var ib_delete = contactItemView.findViewById<ImageButton>(R.id.imageButton_delete)
        //internal var isHomePhone = contactItemView.findViewById<CheckBox>(R.id.checkBox_isHome)
        init{

            contactItemView.isClickable = true
            ib_edit.setOnClickListener{
                mainActivity.showContact(adapterPosition)
            }
            ib_delete.setOnClickListener{
                mainActivity.giveAlert(adapterPosition)
            }
        }
        override fun onClick(v: View?) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.contacts_item,parent,false)
        return ContactViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (contactList != null) {
            return contactList.size
        }
        return -1
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        holder.name.text = contact.name
        holder.surname.text = contact.surname
        holder.phoneNumber.text = contact.phoneNumber.toString()

    }


}