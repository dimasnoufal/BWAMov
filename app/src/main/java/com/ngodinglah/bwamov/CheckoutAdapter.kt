package com.ngodinglah.bwamov

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ngodinglah.bwamov.model.CheckOut
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var data: List<CheckOut>, private var listener: (CheckOut) -> Unit) :
    RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckoutAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_checkout, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CheckoutAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvKursi: TextView = view.findViewById(R.id.tv_kursi_checkout)
        private val tvHarga: TextView = view.findViewById(R.id.tv_harga_checkout)

        fun bindItem(data:CheckOut, listener: (CheckOut) -> Unit, context: Context) {
            val localID = Locale("id", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)
            tvHarga.setText(formatRupiah.format(data.harga!!.toDouble()))

            if (data.kursi!!.startsWith("Total")) {
                tvKursi.setText(data.kursi)
                tvKursi.setCompoundDrawables(null, null, null, null)
            } else {
                tvKursi.setText("Seat No. "+data.kursi)
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

}
