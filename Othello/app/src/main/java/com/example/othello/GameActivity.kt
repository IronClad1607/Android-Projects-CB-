package com.example.othello

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.edit
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(), View.OnClickListener {

    var btnRow = ArrayList<Button>()
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

        Log.d("BtnRow", "${btnRow.size}   ${btnArray[0].size}")

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
            btnRow.clear()
            for (j in 0..7) {
                val btn = Button(this)
                btn.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0F
                )
                if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
                    btn.setBackgroundColor(Color.WHITE)
                }
                if ((i == 4 && j == 3) || (i == 3 && j == 4)) {
                    btn.setBackgroundColor(Color.BLACK)
                }
                horizontalLayout.addView(btn)
                btnRow.add(btn)
            }

            mainLayout.addView(horizontalLayout)
            btnArray.add(btnRow)
        }

    }


}
