package com.example.chessclock.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chessclock.R
import com.example.chessclock.activities.MainActivity
import com.example.chessclock.adapters.SingleClockAdapter
import com.example.chessclock.util.ClockData

class SettingsFrag : Fragment(), SingleClockAdapter.ItemClicked {

    lateinit var recycler : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        recycler=view.findViewById(R.id.recycler)
        recycler.layoutManager= GridLayoutManager(activity as Context, 2, RecyclerView.VERTICAL, false)
        val list = arrayListOf<ClockData>(
            ClockData("Bullet", 1, "min"),
            ClockData("Blitz", 3, "min"),
            ClockData("Blitz", 5, "min"),
            ClockData("Blitz", 7, "min"),
            ClockData("Rapid", 10, "min"),
            ClockData("Rapid", 15, "min"),
            ClockData("Rapid", 20, "min"),
            ClockData("Rapid", 30, "min"),
            ClockData("Rapid", 60, "min"),
        )

        recycler.adapter=SingleClockAdapter(list, activity as Context, this)
        /**recycler.addItemDecoration(

        )**/
        return view
    }

    override fun clicked(data : ClockData) {
        (activity as MainActivity).dat = data
        (context as FragmentActivity).supportFragmentManager.beginTransaction().addToBackStack(null).
        replace(R.id.frame, HomeFrag()).commit()
    }
}