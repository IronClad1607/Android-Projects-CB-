package com.example.othello

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.edit
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(), View.OnClickListener {

    var btnArray = ArrayList<ArrayList<Button>>()
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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

        createBoard()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

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

    private fun createBoard() {
        mainLayout.orientation = LinearLayout.VERTICAL
        mainLayout.weightSum = 8.0F
        for (i in 0..7) {
            val horizontalLayout = LinearLayout(this)
            horizontalLayout.orientation = LinearLayout.HORIZONTAL
            horizontalLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0F
            )
            for (j in 0..7) {
                val btn = Button(this)
                btn.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0F
                )
                horizontalLayout.addView(btn)
            }

            mainLayout.addView(horizontalLayout)
        }

    }


}
