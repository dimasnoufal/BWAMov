package com.ngodinglah.bwamov.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.model.Film

class ComingSoonAdapter(private var data: List<Film>, private var listener: (Film) -> Unit) :
    RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComingSoonAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_coming_soon, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ComingSoonAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tv_title_coming_soon)
        private val tvGenre: TextView = view.findViewById(R.id.tv_genre_coming_soon)
        private val tvRate: TextView = view.findViewById(R.id.tv_rate_coming_soon)
        private val ivPoster: ImageView = view.findViewById(R.id.iv_poster_coming_soon_image)

        fun bindItem(data:Film, listener: (Film) -> Unit, context: Context) {
            tvTitle.setText(data.judul)
            tvGenre.setText(data.genre)
            tvRate.setText(data.rating)

            Glide.with(context)
                .load(data.poster)
                .into(ivPoster)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

}