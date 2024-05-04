package com.example.contacts.Activity

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.contacts.R
import com.example.contacts.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {

    lateinit var permissionBinding: ActivityPermissionBinding
    private val CONTACTS_PERMISSION_REQUEST_CODE = 101
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionBinding = ActivityPermissionBinding.inflate(layoutInflater)
        val view: View = permissionBinding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("permissions", MODE_PRIVATE)

        if (isPermissionGranted()) {
            goToMainActivity()
        }

        permissionBinding.btnPermission.setOnClickListener {
            requestContactsPermission()
        }

    }

    private fun requestContactsPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                CONTACTS_PERMISSION_REQUEST_CODE
            )
        } else {
            onPermissionGranted()
        }
    }

    private fun onPermissionGranted() {
        sharedPreferences.edit().putBoolean("contacts_permission_granted", true).apply()
        Toast.makeText(this, "Contacts permission granted", Toast.LENGTH_SHORT).show()
        goToMainActivity()
    }

    private fun isPermissionGranted(): Boolean {
        return sharedPreferences.getBoolean("contacts_permission_granted", false)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CONTACTS_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    onPermissionGranted()
                } else {
                    Toast.makeText(this, "Contacts permission denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }

}