package com.example.othello

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        setSupportActionBar(tbGame)


        supportActionBar?.title = "Othello!"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pref = getPreferences(Context.MODE_PRIVATE)


        tvPl1.text = intent.getStringExtra("P1")
        tvPl2.text = intent.getStringExtra("P2")

        pref.edit {
            putString("P1", intent.getStringExtra("P1"))
            putString("P2", intent.getStringExtra("P2"))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.btnStop -> {
            Toast.makeText(
                this@GameActivity,
                "Game Stopped",
                Toast.LENGTH_LONG
            ).show()
            Handler().postDelayed({
                finish()
            }, 3000)
            true
        }
        R.id.btnReset -> {
            Toast.makeText(this, "Game Reset!", Toast.LENGTH_SHORT).show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
