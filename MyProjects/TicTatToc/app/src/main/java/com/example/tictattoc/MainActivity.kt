package com.example.tictattoc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val KEY1 = "toGamePageP1"
    val KEY2 = "toGamePageP2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnStart.setOnClickListener {

            val intent = Intent(this, MainGamePage::class.java)

            startActivity(intent)

        }
    }

}


