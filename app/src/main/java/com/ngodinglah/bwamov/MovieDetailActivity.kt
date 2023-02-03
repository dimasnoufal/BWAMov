package com.ngodinglah.bwamov

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.ngodinglah.bwamov.model.Film
import com.ngodinglah.bwamov.model.Plays
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Plays>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val data = intent.getParcelableExtra<Film>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data!!.judul.toString())
            .child("play")

        tv_title_movie_detail.text = data!!.judul
        tv_genre_movie_detail.text = data.genre
        tv_deskripsi_movie_detail.text = data.desc
        tv_rate_movie_detail.text = data.rating

        Glide.with(this@MovieDetailActivity)
            .load(data.poster)
            .into(iv_poster_home_detail)

        rv_aktor_movie_detail.layoutManager =
            LinearLayoutManager(this@MovieDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        getData()

        btn_pilih_bangku.setOnClickListener {
            var pilihBangku = Intent(this@MovieDetailActivity, PilihBangkuScreenActivity::class.java)
                .putExtra("data", dataList)
            startActivity(pilihBangku)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()

                for (getDataSnapshot in dataSnapshot.children) {
                    var film = getDataSnapshot.getValue(Plays::class.java)
                    dataList.add(film!!)
                }

                rv_aktor_movie_detail.adapter = PlaysAdapter(dataList) {

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@MovieDetailActivity,
                    "" + databaseError.message,
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }
}