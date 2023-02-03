package com.ngodinglah.bwamov

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ngodinglah.bwamov.model.Plays

class PlaysAdapter(private var data: List<Plays>, private var listener: (Plays) -> Unit) :
    RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaysAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_aktor, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlaysAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNama: TextView = view.findViewById(R.id.tv_nama_aktor_detail)
        private val ivPoster: ImageView = view.findViewById(R.id.iv_poster_image_aktor_detail)

        fun bindItem(data:Plays, listener: (Plays) -> Unit, context: Context) {
            tvNama.setText(data.nama)

            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(ivPoster)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

}
