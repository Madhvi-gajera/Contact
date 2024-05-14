package com.example.contacts.Helper

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import androidx.loader.content.AsyncTaskLoader
import com.example.contacts.ModelClass.ContactModel

class ContactLoader(context: Context) : AsyncTaskLoader<ArrayList<ContactModel>>(context) {

    override fun loadInBackground(): ArrayList<ContactModel> {
        val contacts: ArrayList<ContactModel> = ArrayList()

        val contactIdsSet = HashSet<String>()

        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Email.ADDRESS,
            ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS,
            ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI
        )

        val contactCursor: Cursor? = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,
            null,
            null,
            "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"
        )

        contactCursor?.use { cursor ->
            while (cursor.moveToNext()) {
                val contactId = cursor.getString(0)
                if (!contactIdsSet.contains(contactId)) {
                    val contact = ContactModel(
                        contactId,
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                    )
                    contacts.add(contact)
                    contactIdsSet.add(contactId)
                }
            }
        }

        contactCursor?.close()
        return contacts
    }

}