package edu.atilim.ise308.inanc.inanc_ipekcansu_mycontacts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import edu.atilim.ise308.inanc.inanc_ipekcansu_mycontacts.db.DataManager

class AddContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val et_name = findViewById<EditText>(R.id.et_Name)
        val et_surname = findViewById<EditText>(R.id.et_Surname)
        val et_phone = findViewById<EditText>(R.id.et_Phone)
        val cb_home_phone = findViewById<CheckBox>(R.id.cb_isHome)
        val btn_add_contact = findViewById<Button>(R.id.btnAdd)


        val dm = DataManager(this)

        btn_add_contact.setOnClickListener {

            val pnum = Integer.parseInt(et_phone.text.toString())
            //dm.insert(et_name.text.toString(),et_surname.text.toString(), et_phone.text.toString())
            dm.insert(et_name.text.toString(),et_surname.text.toString(), pnum.toString())

            // We send the data with Intent to Main Activity
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

            //finish()
        }

    }
}