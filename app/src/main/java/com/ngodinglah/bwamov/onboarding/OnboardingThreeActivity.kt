package com.ngodinglah.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.sign.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_three.*

class OnboardingThreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        btn_memulai_one.setOnClickListener({
            finishAffinity()

            val signIn = Intent(this@OnboardingThreeActivity, SignInActivity::class.java)
            startActivity(signIn)
        })
    }
}