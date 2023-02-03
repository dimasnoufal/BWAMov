package com.ngodinglah.bwamov.home.tiket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.model.CheckOut

class TiketAdapter(private var data: List<CheckOut>, private var listener: (CheckOut) -> Unit) :
    RecyclerView.Adapter<TiketAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TiketAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout_white, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TiketAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvKursi: TextView = view.findViewById(R.id.tv_kursi)

        fun bindItem(data: CheckOut, listener: (CheckOut) -> Unit, context: Context) {
            tvKursi.setText("Seat No." + data.kursi)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

}
