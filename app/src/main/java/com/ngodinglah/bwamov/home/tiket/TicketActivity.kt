package com.ngodinglah.bwamov.home.tiket

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.model.CheckOut
import com.ngodinglah.bwamov.model.Film
import kotlinx.android.synthetic.main.activity_ticket.*

class TicketActivity : AppCompatActivity() {

    private var dataList = ArrayList<CheckOut>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        var data = intent.getParcelableExtra<Film>("data")

        tv_title.text = data!!.judul
        tv_genre.text = data.genre
        tv_rate.text = data.rating

        Glide.with(this@TicketActivity)
            .load(data.poster)
            .into(iv_poster_image)

        rc_checkout.layoutManager = LinearLayoutManager(this@TicketActivity)
        dataList.add(CheckOut("C1", ""))
        dataList.add(CheckOut("C2", ""))

        rc_checkout.adapter = TiketAdapter(dataList) {

        }

        iv_close.setOnClickListener {
            finish()
        }

        iv_barcode.setOnClickListener {
            showDialog("Silahkan melakukan scanning pada counter tiket terdekat")
        }
    }

    private fun showDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_qr)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        val tvDesc = dialog.findViewById(R.id.tv_desc) as TextView
        tvDesc.text = title

        val btnClose = dialog.findViewById(R.id.btn_close) as Button
        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }
}