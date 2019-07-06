package com.example.othello

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.view.*

class GameActivity : AppCompatActivity(), View.OnClickListener {

    var btnRow = ArrayList<CustomButton>()
    var btnArray = ArrayList<ArrayList<CustomButton>>()

    var gameOver: Boolean = false
    var blackTurn: Boolean = false
    var bc: Int = 0
    var wc: Int = 0
    var counter: Int = 0

    override fun onClick(p0: View?) {

        val button = p0 as CustomButton
        if (button.clicked || gameOver || !button.isCanClicked)
            return
        if (blackTurn) {
            button.black = true
            button.clicked = true
            button.setBackgroundColor(Color.BLACK)
            bc++
        } else {
            button.setBackgroundColor(Color.WHITE)
            button.black = false
            button.clicked = true
            wc++
        }

        if (blackTurn) {
            checkBlack(button.atX, button.atY)
        } else {
            checkWhite(button.atX, button.atY)
        }

        button.clicked = true

        tvScoreP1.text = bc.toString()
        tvScoreP2.text = wc.toString()

        blackTurn = !blackTurn
        counter = updateBoard(btnArray, blackTurn)
        if (counter == 0) {
            blackTurn = !blackTurn
            counter = updateBoard(btnArray, blackTurn)
            if (counter == 0) {
                gameOver = true
                if (bc > wc) {
                    Toast.makeText(this, "Black Won!!", Toast.LENGTH_LONG).show()
                } else if (wc > bc) {
                    Toast.makeText(this, "White Won!!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Draw!!", Toast.LENGTH_LONG).show()
                }
            } else
                Toast.makeText(this, "Pass!!", Toast.LENGTH_SHORT).show()

        }
    }

    private fun checkWhite(row: Int, col: Int) {
        var i: Int
        var j: Int
        var count: Int


        //Up
        i = row - 1
        j = col
        count = 0
        while (i >= 0 && btnArray[i][j].clicked && !btnArray[i][j].black) {
            count++
            i--
        }
        if ((i >= 0 && i < 8 && j >= 0 && j < 8 && !btnArray[i][j].clicked) || !(i >= 0 && i < 8 && j >= 0 && j < 8)) {
            count = 0
        }
        bc += count
        wc -= count
        while (count > 0) {
            btnArray[row - count][col].setBackgroundColor(Color.BLACK)
            btnArray[row - count][col].black = true
            count--
        }


        //Down
        i = row + 1
        j = col
        count = 0
        while (i < 8 && btnArray[i][j].clicked && !btnArray[i][j].black) {
            count++
            i++
        }
        if ((i >= 0 && i < 8 && j >= 0 && j < 8 && !btnArray[i][j].clicked) || !(i >= 0 && i < 8 && j >= 0 && j < 8)) {
            count = 0
        }
        bc += count
        wc -= count
        while (count > 0) {
            btnArray[row - count][col].setBackgroundColor(Color.BLACK)
            btnArray[row - count][col].black = true
            count--
        }

        //Left
        i = row
        j = col -1
        count = 0
        while (j >= 0 && btnArray[i][j].clicked && !btnArray[i][j].black) {
            count++
            j--
        }
        if ((i >= 0 && i < 8 && j >= 0 && j < 8 && !btnArray[i][j].clicked) || !(i >= 0 && i < 8 && j >= 0 && j < 8)) {
            count = 0
        }
        bc += count
        wc -= count
        while (count > 0) {
            btnArray[row - count][col].setBackgroundColor(Color.BLACK)
            btnArray[row - count][col].black = true
            count--
        }

        //Right
        i = row
        j = col + 1
        count = 0
        while (j <8  && btnArray[i][j].clicked && !btnArray[i][j].black)
        {
            count++
            j++
        }
        if ((i >= 0 && i < 8 && j >= 0 && j < 8 && !btnArray[i][j].clicked) || !(i >= 0 && i < 8 && j >= 0 && j < 8))
        {
            count = 0
        }
        bc += count
        wc -= count
        while (count > 0)
        {
            btnArray[row - count][col].setBackgroundColor(Color.BLACK)
            btnArray[row - count][col].black = true
            count--
        }

    }

    private fun checkBlack(row: Int, col: Int) {
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

        gameOver = false
        blackTurn = true
        wc = 2
        bc = 2

        createBoard()
        counter = updateBoard(btnArray, blackTurn)


        tvScoreP1.text = bc.toString()
        tvScoreP2.text = wc.toString()

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
                val btn = CustomButton(this)
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
                btn.setOnClickListener(this)
                horizontalLayout.addView(btn)
                btnRow.add(btn)
            }

            mainLayout.addView(horizontalLayout)
            btnArray.add(btnRow)
        }

    }


}
