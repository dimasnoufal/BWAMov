package com.ngodinglah.bwamov.home.tiket

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.home.dashboard.ComingSoonAdapter
import com.ngodinglah.bwamov.model.Film
import com.ngodinglah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.fragment_ticket.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TicketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TicketFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference
    private var datalist = ArrayList<Film>()


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
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(requireContext())
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        rc_tiket.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                datalist.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {
                    val film = getdataSnapshot.getValue(Film::class.java!!)
                    datalist.add(film!!)
                }

                rc_tiket.adapter = ComingSoonAdapter(datalist) {
                    var detailTicket = Intent(context, TicketActivity::class.java).putExtra("data", it)
                    startActivity(detailTicket)
                }

                tv_total.setText("${datalist.size} Movies")

            }

            override fun onCancelled(DatabaseError: DatabaseError) {
                Toast.makeText(context, " "+DatabaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TicketFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TicketFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}