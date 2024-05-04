package com.example.contacts.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.ModelClass.ContactModel
import com.example.contacts.Adapter.ContactsAdapter
import com.example.contacts.Helper.ContactLoader
import com.example.contacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , LoaderManager.LoaderCallbacks<ArrayList<ContactModel>>{

    lateinit var contactAdapter: ContactsAdapter
    lateinit var contactsList: ArrayList<ContactModel>

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)

        contactsList = ArrayList()

        binding.rcvMain.layoutManager = LinearLayoutManager(this)
        contactAdapter = ContactsAdapter(this, contactsList)
        binding.rcvMain.adapter = contactAdapter
        supportLoaderManager.initLoader(1, null, this).forceLoad()

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<ArrayList<ContactModel>> {
        return ContactLoader(this@MainActivity)
    }

    override fun onLoaderReset(loader: Loader<ArrayList<ContactModel>>) {

    }

    override fun onLoadFinished(
        loader: Loader<ArrayList<ContactModel>>,
        data: ArrayList<ContactModel>?
    ) {
        Log.e("contactsList:::",""+data)
        if (data != null) {
            contactsList.clear()
            contactsList.addAll(data)
            contactAdapter.notifyDataSetChanged()
        }
    }
}