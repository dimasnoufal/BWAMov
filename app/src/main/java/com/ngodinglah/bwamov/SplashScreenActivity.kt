package com.ngodinglah.bwamov

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.ngodinglah.bwamov.onboarding.OnboardingOneActivity

/*
    ini adalah splash screen
 */

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val onBoardingOne = Intent(this@SplashScreenActivity, OnboardingOneActivity::class.java)
            startActivity(onBoardingOne)
            finish()
        }, 5000)
    }
}