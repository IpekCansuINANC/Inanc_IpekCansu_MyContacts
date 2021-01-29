package edu.atilim.ise308.inanc.inanc_ipekcansu_mycontacts.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import edu.atilim.ise308.inanc.inanc_ipekcansu_mycontacts.ContactModel


private const val TAG = "DataManager"

class DataManager(context: Context) {

    private val db: SQLiteDatabase

    init {
        db = CustomSQLiteOpenHelper(context).getWritableDatabase()
    }

    // Inserting a data to database
    fun insert(name: String, surname: String, phone_number: String){
        val insertQuery = " INSERT INTO " + TABLE_CONTACT +
                " ( " + NAME + ", " + SURNAME + ", " + PHONE_NUMBER + " ) VALUES ( '" +
                name + "', '" + surname + "', '" + phone_number + "'); "

        Log.i(TAG, "------> insert: $insertQuery")
        db.execSQL(insertQuery)
    }

    // Reading the data to list from database
    fun readData(): MutableList<ContactModel>{
        val list : MutableList<ContactModel> = ArrayList()
        val resultQuery = "SELECT * FROM " + TABLE_CONTACT + " ; "
        Log.i(TAG, "-----> onreadData: $resultQuery")
        val resultsCursor = db.rawQuery(resultQuery, null)
        if(resultsCursor.moveToFirst()){
            do{
                val contact = ContactModel()
                contact.name = resultsCursor.getString(1)
                contact.surname = resultsCursor.getString(2)
                contact.phoneNumber = Integer.parseInt(resultsCursor.getString(3))
                list.add(contact)
            }while (resultsCursor.moveToNext())
        }
        return list
    }

    //Delete a contact from database
    fun deleteContact(name: String){
        val removeQuery = " DELETE FROM " + TABLE_CONTACT + " WHERE " + NAME + " = '" + name + "';"
        Log.i(TAG, "------> remove: $removeQuery")
        db.execSQL(removeQuery)
    }


    //Updating contact
    fun updateContact(updContact: ContactModel){
        val values = ContentValues()
        values.put(NAME, updContact.name)
        values.put(SURNAME, updContact.surname)
        values.put(PHONE_NUMBER, updContact.phoneNumber)
        db.update(TABLE_CONTACT, values, NAME + "=?", arrayOf(updContact.name.toString())).toLong()

    }


    private inner class CustomSQLiteOpenHelper(context : Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
        override fun onCreate(db: SQLiteDatabase?) {
            //Oluşturacağımız tablo
            val newTableQuery = " CREATE TABLE " + TABLE_CONTACT + " ( " +
                    _ID + " integer primary key autoincrement not null, " +
                    NAME + " text not null, " +
                    SURNAME + " text not null, " +
                    PHONE_NUMBER + " text not null); "
            Log.i(TAG, "-----> onCreate: $newTableQuery")

            db?.execSQL(newTableQuery)

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("Not yet implemented")
        }


    }

    companion object{
        private const val DB_NAME = "contact_database"
        private const val DB_VERSION = 1
        private const val TABLE_CONTACT = "contact_table"
        private const val _ID = "contact_id"
        private const val NAME = "contact_name"
        private const val SURNAME = "contact_surname"
        private const val PHONE_NUMBER = "contact_phone"
        private const val IS_HOME_PHONE = "contact_isHomePhone"

    }
}