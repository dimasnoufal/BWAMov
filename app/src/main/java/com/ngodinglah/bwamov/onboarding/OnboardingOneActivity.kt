package com.ngodinglah.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.sign.SignInActivity
import com.ngodinglah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.activity_onboarding_one.*

class OnboardingOneActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)

        preferences = Preferences(this@OnboardingOneActivity)

        if (preferences.getValues("onboarding").equals("1")) {
            finishAffinity()

            val goSignIn = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(goSignIn)
        }

        btn_lanjutkan_one.setOnClickListener{
            val onboardingTwo = Intent(this@OnboardingOneActivity, OnboardingTwoActivity::class.java)
            startActivity(onboardingTwo)
        }

        btn_lewat_one.setOnClickListener{
            finishAffinity()

            val signIn = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(signIn)
        }
    }
}