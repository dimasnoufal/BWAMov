package com.ngodinglah.bwamov.wallet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.home.HomeScreenActivity
import kotlinx.android.synthetic.main.activity_my_wallet_success.*

class MyWalletSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet_success)

        btn_home.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@MyWalletSuccessActivity,
                HomeScreenActivity::class.java)
            startActivity(intent)
        }

    }
}