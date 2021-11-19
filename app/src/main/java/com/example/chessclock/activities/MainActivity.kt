package com.example.chessclock.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.example.chessclock.R
import com.example.chessclock.fragment.HomeFrag
import com.example.chessclock.util.ClockData

class MainActivity : AppCompatActivity() {
    lateinit var frame : FrameLayout
    var dat: ClockData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        frame = findViewById(R.id.frame);
        supportFragmentManager.beginTransaction().replace(R.id.frame, HomeFrag()).commit()
    }


    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}