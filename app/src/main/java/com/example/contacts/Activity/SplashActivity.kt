package com.example.contacts.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.contacts.R
import com.example.contacts.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var handler : Handler
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)

        handler = Handler()

        val runnableCode = Runnable {
            val intent = Intent(this@SplashActivity, PermissionActivity::class.java)
            startActivity(intent)
            finish()
        }
        handler.postDelayed(runnableCode, 3000)

    }
}