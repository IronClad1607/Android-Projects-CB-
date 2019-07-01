package com.example.othello

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val pref = getPreferences(Context.MODE_PRIVATE)


        tvPl1.text = intent.getStringExtra("P1")
        tvPl2.text = intent.getStringExtra("P2")

        pref.edit {
            putString("P1",intent.getStringExtra("P1"))
            putString("P2",intent.getStringExtra("P2"))
        }

    }
}
