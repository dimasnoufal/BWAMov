package com.ngodinglah.bwamov

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngodinglah.bwamov.home.tiket.TicketActivity
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

            showNotif(data)

        }

        btn_batalkan_checkout.setOnClickListener {
            finish()
        }

        rv_checkout.layoutManager = LinearLayoutManager(this@CheckoutScreenActivity)
        rv_checkout.adapter = CheckoutAdapter(dataList) {

        }
    }

    private fun showNotif(datas: Film?) {
        val NOTIFICATION_CHANNEL_ID = "channel_bwa_notif"
        val context = this.applicationContext
        var notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channelName = "BWAMOV Notif Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }

//        val mIntent = Intent(this@CheckoutScreenActivity, CheckoutSuksesActivity::class.java)
//        val bundle = Bundle()
//        bundle.putString("id", "id_film")
//        mIntent.putExtras(bundle)

        val mIntent = Intent(this, TicketActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("data", datas)
        mIntent.putExtras(bundle)

        val pendingIntent =
            PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.logo_mov)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    R.mipmap.ic_launcher
                )
            )
            .setTicker("notif bwa starting")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.RED, 3000, 3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Sukses Terbeli")
            .setContentText("Tiket " + datas!!.judul + " berhasil kamu dapatkan. Enjoy the movie!")



        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115, builder.build())
    }
}