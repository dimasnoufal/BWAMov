package com.ngodinglah.bwamov

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ngodinglah.bwamov.model.CheckOut
import com.ngodinglah.bwamov.model.Film
import kotlinx.android.synthetic.main.activity_pilih_bangku_screen.*

class PilihBangkuScreenActivity : AppCompatActivity() {

    var statusA1: Boolean = false
    var statusA2: Boolean = false
    var statusA3: Boolean = false
    var statusA4: Boolean = false

    var statusB1: Boolean = false
    var statusB2: Boolean = false
    var statusB3: Boolean = false
    var statusB4: Boolean = false

    var statusC1: Boolean = false
    var statusC2: Boolean = false
    var statusC3: Boolean = false
    var statusC4: Boolean = false

    var statusD1: Boolean = false
    var statusD2: Boolean = false
    var statusD3: Boolean = false
    var statusD4: Boolean = false

    var total: Int = 0

    private var dataList = ArrayList<CheckOut>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku_screen)

        val data = intent.getParcelableExtra<Film>("data")
        if (data != null) {
            tv_judul_pilih_bangku.text = data.judul
        }

        iv_back_pilih_bangku.setOnClickListener {
            val back = Intent(this@PilihBangkuScreenActivity, MovieDetailActivity::class.java)
            startActivity(back)
            finish()
        }

        iv_bangku_a_1.setOnClickListener {
            if (statusA1) {
                iv_bangku_a_1.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusA1 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("A1", "50000"))

            } else {
                iv_bangku_a_1.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusA1 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("A1", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_a_2.setOnClickListener {
            if (statusA2) {
                iv_bangku_a_2.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusA2 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("A2", "50000"))

            } else {
                iv_bangku_a_2.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusA2 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("A2", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_a_3.setOnClickListener {
            if (statusA3) {
                iv_bangku_a_3.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusA3  = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("A3", "50000"))

            } else {
                iv_bangku_a_3.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusA3 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("A3", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_a_4.setOnClickListener {
            if (statusA4) {
                iv_bangku_a_4.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusA4 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("A4", "50000"))

            } else {
                iv_bangku_a_4.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusA4 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("A4", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_b_1.setOnClickListener {
            if (statusB1) {
                iv_bangku_b_1.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusB1 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("B1", "50000"))

            } else {
                iv_bangku_b_1.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusB1 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("B1", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_b_2.setOnClickListener {
            if (statusB2) {
                iv_bangku_b_2.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusB2 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("B2", "50000"))

            } else {
                iv_bangku_b_2.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusB2 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("B2", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_b_3.setOnClickListener {
            if (statusB3) {
                iv_bangku_b_3.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusB3  = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("B3", "50000"))

            } else {
                iv_bangku_b_3.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusB3 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("B3", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_b_4.setOnClickListener {
            if (statusB4) {
                iv_bangku_b_4.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusB4 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("B4", "50000"))

            } else {
                iv_bangku_b_4.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusB4 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("B4", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_c_1.setOnClickListener {
            if (statusC1) {
                iv_bangku_c_1.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusC1 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("C1", "50000"))

            } else {
                iv_bangku_c_1.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusC1 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("C1", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_c_2.setOnClickListener {
            if (statusC2) {
                iv_bangku_c_2.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusC2 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("C2", "50000"))

            } else {
                iv_bangku_c_2.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusC2 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("C2", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_c_3.setOnClickListener {
            if (statusC3) {
                iv_bangku_c_3.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusC3  = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("C3", "50000"))

            } else {
                iv_bangku_c_3.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusC3 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("C3", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_c_4.setOnClickListener {
            if (statusC4) {
                iv_bangku_c_4.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusC4 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("C4", "50000"))

            } else {
                iv_bangku_c_4.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusC4 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("C4", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_d_1.setOnClickListener {
            if (statusD1) {
                iv_bangku_d_1.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusD1 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("D1", "50000"))

            } else {
                iv_bangku_d_1.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusD1 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("D1", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_d_2.setOnClickListener {
            if (statusD2) {
                iv_bangku_d_2.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusD2 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("D2", "50000"))

            } else {
                iv_bangku_d_2.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusD2 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("D2", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_d_3.setOnClickListener {
            if (statusD3) {
                iv_bangku_d_3.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusD3  = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("D3", "50000"))


            } else {
                iv_bangku_d_3.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusD3 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("D3", "50000")
                dataList.add(data)
            }
        }

        iv_bangku_d_4.setOnClickListener {
            if (statusD4) {
                iv_bangku_d_4.setImageResource(R.drawable.ic_rectangle_empty_50)
                statusD4 = false
                total -= 1
                beliTiket(total)

                dataList.remove(CheckOut("D4", "50000"))

            } else {
                iv_bangku_d_4.setImageResource(R.drawable.ic_rectangle_selected_50)
                statusD4 = true
                total += 1
                beliTiket(total)

                val data = CheckOut("D4", "50000")
                dataList.add(data)
            }
        }

        btn_beli_tiket.setOnClickListener {
            val beliTiket =
                Intent(this@PilihBangkuScreenActivity, CheckoutScreenActivity::class.java)
                    .putExtra("data", dataList).putExtra("datas", data)
            startActivity(beliTiket)

        }

    }

    private fun beliTiket(total: Int) {
        if (total == 0) {
            btn_beli_tiket.setText("Beli Tiket")
            btn_beli_tiket.visibility = View.INVISIBLE
        } else {
            btn_beli_tiket.setText("Beli Tiket (" + total + ")")
            btn_beli_tiket.visibility = View.VISIBLE
        }
    }
}