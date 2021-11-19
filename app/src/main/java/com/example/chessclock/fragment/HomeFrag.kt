package com.example.chessclock.fragment

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.chessclock.R
import com.example.chessclock.activities.MainActivity
import com.example.chessclock.adapters.SingleClockAdapter
import com.example.chessclock.util.ClockData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFrag : Fragment(){

    lateinit var card_a : View
    lateinit var card_b : View
    private lateinit var btn_settings : FloatingActionButton
    private lateinit var btn_restart : FloatingActionButton
    private lateinit var btn_pause : FloatingActionButton
    lateinit var tv_a : TextView
    lateinit var tv_b : TextView
    lateinit var clock_a : CountDownTimer
    lateinit var clock_b : CountDownTimer
    var a_running  = false
    var b_running = false
    var time_left_a = 10000L
    var time_left_b = 10000L
    lateinit var soundClick : MediaPlayer
    lateinit var soundLost: MediaPlayer


    var gameType = arguments?.get("gameType")
    var gameTime = arguments?.get("gameTime")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        card_a = view.findViewById(R.id.card_a)
        card_b = view.findViewById(R.id.card_b)
        tv_a = view.findViewById(R.id.tv_a)
        tv_b = view.findViewById(R.id.tv_b)
        btn_settings = view.findViewById(R.id.btn_settings)
        btn_pause = view.findViewById(R.id.btn_pause)
        btn_restart = view.findViewById(R.id.btn_restart)
        card_a.setBackgroundResource(R.drawable.card_a_bkd_offturn)
        card_b.setBackgroundResource(R.drawable.card_b_bkd_offturn)

        tv_a.text = (activity as MainActivity).dat?.gameTime.toString()
        card_a.setOnClickListener {

            if (b_running){

            }
            else if (a_running){
                setClockB(time_left_b)
                a_running = false
                clock_a.cancel()
                //change A's UI
                card_a.setBackgroundResource(R.drawable.card_a_bkd_offturn)
                val soundClick = MediaPlayer.create(activity as Context, R.raw.on_tap)
                soundClick.start()
                soundClick.setOnCompletionListener {
                    soundClick.release()
                }
                clock_b.start()
            }
            else {
                setClockB(time_left_b)
                val soundClick = MediaPlayer.create(activity as Context, R.raw.on_tap)
                soundClick.start()
                soundClick.setOnCompletionListener {
                    soundClick.release()
                }
                clock_b.start()
            }
        }

        card_b.setOnClickListener {
            if (a_running) {

            }
            else if (b_running){

                setClockA(time_left_a)
                b_running = false
                clock_b.cancel()
                card_b.setBackgroundResource(R.drawable.card_b_bkd_offturn)
                soundClick = MediaPlayer.create(activity as Context, R.raw.on_tap)
                soundClick.start()
                soundClick.setOnCompletionListener {
                    soundClick.release()
                }
                clock_a.start()
            }
            else {
                setClockA(time_left_a)
                soundClick = MediaPlayer.create(activity as Context, R.raw.eatmypussy)
                soundClick.start()
                soundClick.setOnCompletionListener {
                    soundClick.release()
                }
                clock_a.start()
            }
        }

        btn_restart.setOnClickListener {
            Toast.makeText(activity as Context, "nothing yet done", Toast.LENGTH_SHORT).show()
        }

        btn_settings.setOnClickListener {
            //TODO : make this fragment transaction work well on backstack
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame, SettingsFrag())?.addToBackStack("SettingsFrag")
                ?.commit()
        }

        btn_pause.setOnClickListener {
            if(a_running) clock_a.cancel()
            if(b_running) clock_b.cancel()
            //var animation = AnimationUtils.loadAnimation(activity as Context, R.anim.blinking_text)
            //tv_a.startAnimation(animation)
            //tv_b.startAnimation(animation)
        }
        return view
    }

    private fun setClockA(time_a : Long) {
        card_a.setBackgroundResource(R.drawable.card_a_bkd_inturn)
        a_running = true
        clock_a = object : CountDownTimer(time_a, 1){
            override fun onTick(time_left: Long) {
                var min = (time_left /1000) / 60
                var sec = (time_left/1000)
                var ms = (time_left/100)%10
                if(time_left in 10001..20001)
                    tv_a.text = "$min:$sec.$ms"
                else if (time_left<10000)
                    tv_a.text = "$min:0$sec.$ms"
                else
                    tv_a.text = "$min:$sec"
                time_left_a = time_left
            }

            override fun onFinish() {
                tv_a.text = "TIME UP"
                soundLost = MediaPlayer.create(activity as Context, R.raw.on_lost)
                soundLost.start()
                card_a.setBackgroundResource(R.drawable.card_a_timeout)
            }

        }
    }

    private fun setClockB(time_b : Long) {
        card_b.setBackgroundResource(R.drawable.card_b_bkd_inturn)
        b_running = true
        clock_b = object : CountDownTimer(time_b, 1) {
            override fun onTick(time_left: Long) {
                var min = (time_left /1000) / 60
                var sec = (time_left/1000)
                var ms = (time_left/100)%10
                if(time_left in 10001..20001)
                    tv_b.text = "$min:$sec.$ms"
                else if (time_left<10000)
                    tv_b.text = "$min:0$sec.$ms"
                else
                    tv_b.text = "$min:$sec"
                time_left_b = time_left
            }

            override fun onFinish() {
                tv_b.text = "TIME UP"
                soundLost = MediaPlayer.create(activity as Context, R.raw.on_lost)
                soundLost.start()
                card_b.setBackgroundResource(R.drawable.card_b_timeout)
            }

        }
    }
}