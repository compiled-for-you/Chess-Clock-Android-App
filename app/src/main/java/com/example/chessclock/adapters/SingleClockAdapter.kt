package com.example.chessclock.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chessclock.R
import com.example.chessclock.fragment.SettingsFrag
import com.example.chessclock.util.ClockData

class SingleClockAdapter(private var data: ArrayList<ClockData>, private val context: Context, private val clickListener: ItemClicked):RecyclerView.Adapter<SingleClockAdapter.SingleClockViewHolder>(){

    class SingleClockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_game : TextView = itemView.findViewById(R.id.tv_game)
        val tv_time : TextView = itemView.findViewById(R.id.tv_time)
        val tv_min : TextView = itemView.findViewById(R.id.tv_min)
        val menu_item : LinearLayout = itemView.findViewById(R.id.menuItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleClockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_clock_menu, parent, false)
        return SingleClockViewHolder(view)
    }

    override fun onBindViewHolder(holder: SingleClockViewHolder, position: Int) {
        val item = data[position]
        holder.tv_game.text = item.gameType
        holder.tv_time.text = item.gameTime.toString()
        holder.tv_min.text = item.min

        holder.menu_item.setOnClickListener {
            clickListener.clicked(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ItemClicked{
        fun clicked(data : ClockData)
    }
}