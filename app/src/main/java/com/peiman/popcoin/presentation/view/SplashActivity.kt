package com.peiman.popcoin.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peiman.popcoin.R
import android.content.Intent
import android.os.Handler
import android.os.Looper



/**
 * SplashActivity is the first screen shown when the app launches.
 * It displays a splash screen and transitions to the MainActivity after a timeout.
 */
class SplashActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 3000 // Timeout duration in milliseconds (3 seconds)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the splash screen layout
        setContentView(R.layout.activity_splash)

        // Handler to delay starting MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashTimeOut)

    }
}