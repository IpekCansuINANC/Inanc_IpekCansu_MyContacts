package edu.atilim.ise308.inanc.inanc_ipekcansu_mycontacts

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import edu.atilim.ise308.inanc.inanc_ipekcansu_mycontacts.db.DataManager

class EditDialogFragment: DialogFragment() {

    private var contact: ContactModel? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(this.activity!!)
        val inflater = activity!!.layoutInflater

        val dialogLayout = inflater.inflate(R.layout.dialog_edit_contact,null)

        val editName = dialogLayout.findViewById<EditText>(R.id.editText_Name)
        val editSurname = dialogLayout.findViewById<EditText>(R.id.editText_Surname)
        val editPhoneNumber = dialogLayout.findViewById<EditText>(R.id.editText_Phone)
        val checkIsHome = dialogLayout.findViewById<CheckBox>(R.id.checkBox_isHome)
        val btnDone = dialogLayout.findViewById<Button>(R.id.btnDone)


        // displays editable fields populated with item data.
        editName.setText(contact!!.name)
        editSurname.setText(contact!!.surname)
        editPhoneNumber.setText(contact!!.phoneNumber.toString())
        checkIsHome.isChecked = contact!!.isHomePhone

        val dm =DataManager(activity!!)

        btnDone.setOnClickListener{
            val updatedContact = ContactModel()
            updatedContact.name  = editName.text.toString()
            updatedContact.surname = editSurname.text.toString()
            updatedContact.phoneNumber = Integer.parseInt(editPhoneNumber.text.toString())
            dm.updateContact(updatedContact)

            // Refresh and upload activity again
            val i = Intent(context, MainActivity::class.java)
            context!!.startActivity(i)
            dismiss()
        }

        builder.setView(dialogLayout).setMessage(resources.getString((R.string.your_contact)))

        return builder.create()

    }

    fun sendContactSelected(contactSelected: ContactModel)
    {
        contact = contactSelected
    }

}