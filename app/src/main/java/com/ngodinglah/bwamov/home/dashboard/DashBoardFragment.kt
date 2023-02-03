package com.ngodinglah.bwamov.home.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.ngodinglah.bwamov.MovieDetailActivity
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.model.Film
import com.ngodinglah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.fragment_dash_board.*
import java.text.NumberFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashBoardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashBoardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_board, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        preferences =
            Preferences(requireActivity().applicationContext) // kalo error lihat video kembali di menit 7:51
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        tv_username.setText(preferences.getValues("nama"))
        if (!preferences.getValues("saldo").equals("")){
            currency(preferences.getValues("saldo")!!.toDouble(), tv_saldo)
        }

        Glide.with(this@DashBoardFragment)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        rv_now_playing.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_coming_soon.layoutManager =
            LinearLayoutManager(requireContext().applicationContext)

        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {
                    var film = getdataSnapshot.getValue(Film::class.java!!)
                    dataList.add(film!!)
                }

                if (dataList.isNotEmpty()) {
                    rv_now_playing.adapter = NowPlayingAdapter(dataList) {
                        val intent = Intent(
                            context,
                            MovieDetailActivity::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }

                    rv_coming_soon.adapter = ComingSoonAdapter(dataList) {
                        val intent = Intent(
                            context,
                            MovieDetailActivity::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "" + databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun currency(harga: Double, textView: TextView) {
        val localId = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localId)
        textView.setText(format.format(harga))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashBoardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashBoardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}