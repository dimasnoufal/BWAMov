package com.ngodinglah.bwamov

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngodinglah.bwamov.model.CheckOut
import com.ngodinglah.bwamov.model.Film
import com.ngodinglah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout_screen.*

class CheckoutScreenActivity : AppCompatActivity() {

    private var dataList = ArrayList<CheckOut>()
    private var total: Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_screen)

        preferences = Preferences(this@CheckoutScreenActivity)
        dataList = intent.getSerializableExtra("data") as ArrayList<CheckOut> /* = java.util.ArrayList<com.ngodinglah.bwamov.model.CheckOut> */
        val data = intent.getParcelableExtra<Film>("datas")

        for (a in dataList.indices) {
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(CheckOut("Total harus dibayar", total.toString()))

        btn_bayar_sekarang_checkout.setOnClickListener {
            val sukses = Intent(this@CheckoutScreenActivity, CheckoutSuksesActivity::class.java)
            startActivity(sukses)

        }

        rv_checkout.layoutManager = LinearLayoutManager(this@CheckoutScreenActivity)
        rv_checkout.adapter = CheckoutAdapter(dataList) {

        }
    }
}