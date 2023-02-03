package com.ngodinglah.bwamov

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ngodinglah.bwamov.home.HomeScreenActivity
import kotlinx.android.synthetic.main.activity_checkout_sukses.*

class CheckoutSuksesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_sukses)

        btn_home.setOnClickListener {
            finishAffinity()

            var backHome = Intent(this@CheckoutSuksesActivity, HomeScreenActivity::class.java)
            startActivity(backHome)
        }
    }
}