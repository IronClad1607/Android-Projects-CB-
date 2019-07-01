package com.example.othello

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {

            val intent = Intent(this,GameActivity::class.java)
            intent.putExtra("P1",etP1.text.toString())
            intent.putExtra("P2",etP2.text.toString())
            startActivity(intent)
        }
    }
}
