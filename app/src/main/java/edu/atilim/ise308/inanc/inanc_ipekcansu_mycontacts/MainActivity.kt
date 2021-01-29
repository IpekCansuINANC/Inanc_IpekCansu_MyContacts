package edu.atilim.ise308.inanc.inanc_ipekcansu_mycontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.atilim.ise308.inanc.inanc_ipekcansu_mycontacts.db.DataManager

private const val TAG = "Main Activity TAG"
class MainActivity : AppCompatActivity() {

    companion object{
        var contactList = ArrayList<ContactModel>()
    }
    private var recyclerView: RecyclerView? = null
    private var adapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dm = DataManager(this)

        //We read the list which came from database to the contactlist
        contactList = dm.readData() as ArrayList<ContactModel>

        recyclerView = findViewById<View>(R.id.recylerView) as RecyclerView?
        adapter = ContactAdapter(this, contactList!!)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager=layoutManager
        recyclerView!!.itemAnimator= DefaultItemAnimator()
        recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView!!.adapter = adapter

    }

    //Deleting a selected contact after the confirmation from the user.
    fun giveAlert(contactToDelete: Int){
        val dm = DataManager(this)
        val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Do you want to delete the person ?")
                .setPositiveButton("YES") { dialog, i ->
                    //Log.i(TAG, "giveAlert: ${contactList[contactToDelete].name}")
                    dm.deleteContact(contactList[contactToDelete].name.toString())
                    finish()
                    dialog.dismiss()
                    startActivity(getIntent())
                }
                .setNegativeButton("NO") { dialog, i ->
                    dialog.dismiss()
                }
        dialog.show()
    }

    fun showContact(contactToEdit: Int)
    {
        val dialog = EditDialogFragment()
        contactList?.get(contactToEdit)?.let { dialog.sendContactSelected(it) }
        dialog.show(supportFragmentManager,"")
        adapter!!.notifyDataSetChanged()


    }

    // When we click add button it will open adding activity page
    fun openAddActivity(){
        val changePage = Intent(this,AddContactActivity::class.java)
        startActivity(changePage)
    }


    // Add button on action bar part
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.add_contact, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val a = when (id ){
            R.id.action_add -> {
                openAddActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return a
    }

}