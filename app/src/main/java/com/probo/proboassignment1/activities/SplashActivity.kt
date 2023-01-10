package com.probo.proboassignment1.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.probo.proboassignment1.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition{true}

        @Suppress("DEPRECATION")
        Handler().postDelayed({
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }, 2000)
    }
}