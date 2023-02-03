package com.ngodinglah.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ngodinglah.bwamov.onboarding.OnboardingOneActivity

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