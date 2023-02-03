package com.ngodinglah.bwamov.home

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.home.dashboard.DashBoardFragment
import com.ngodinglah.bwamov.home.setting.SettingFragment
import com.ngodinglah.bwamov.home.tiket.TicketFragment
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val fragmentHome = DashBoardFragment()
        val fragmentTicket = TicketFragment()
        val fragmentSetting = SettingFragment()

        setFragment(fragmentHome)

        iv_menu_home.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(iv_menu_home, R.drawable.ic_home_active)
            changeIcon(iv_menu_ticket, R.drawable.ic_tiket)
            changeIcon(iv_menu_profile, R.drawable.ic_profile)
        }

        iv_menu_ticket.setOnClickListener {
            setFragment(fragmentTicket)

            changeIcon(iv_menu_home, R.drawable.ic_home)
            changeIcon(iv_menu_ticket, R.drawable.ic_tiket_active)
            changeIcon(iv_menu_profile, R.drawable.ic_profile)
        }

        iv_menu_profile.setOnClickListener {
            setFragment(fragmentSetting)

            changeIcon(iv_menu_home, R.drawable.ic_home)
            changeIcon(iv_menu_ticket, R.drawable.ic_tiket)
            changeIcon(iv_menu_profile, R.drawable.ic_profile_active)
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int) {
        imageView.setImageResource(int)
    }
}