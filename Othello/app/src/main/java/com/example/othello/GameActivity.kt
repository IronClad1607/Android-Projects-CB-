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

class GameActivity : AppCompatActivity(), View.OnClickListener {

    var btnRow = ArrayList<CustomButton>()
    var btnArray = ArrayList<ArrayList<CustomButton>>()

    var gameOver: Boolean = false
    var blackTurn: Boolean = false
    var bc: Int = 0
    var wc: Int = 0
    var counter: Int = 0
    override fun onClick(p0: View?) {

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
        updateBoard()


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

                btnArray[i][j].setX(i)
                btnArray[i][j].setY(j)
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


    private fun updateBoard() {
        counter = 0
        var valid: Int = 0
        var i: Int
        var j: Int
        var flag: Boolean
        for (olc in 0..7) {
            for (ilc in 0..7) {
                if (btnArray[olc][ilc].clicked)
                    continue
                if (blackTurn) {
                    i = olc + 1
                    j = ilc + 1
                    flag = false
                    valid = 0

                    //Diagonal Right - Down
                    while (i < 8 && j < 8 && btnArray[i][j].clicked) {
                        if (btnArray[i][j].black && valid == 0) {
                            break
                        }
                        if (!btnArray[i][j].black) {
                            valid = 1
                        }
                        if (valid == 1 && btnArray[i][j].black) {
                            flag = true
                            break
                        }
                        i++
                        j++
                    }
                    i = olc - 1
                    j = ilc - 1
                    valid = 0

                    //Up Left
                    while (i >= 0 && j >= 0 && !flag && btnArray[i][j].clicked) {
                        if (btnArray[i][j].black && valid == 0) {
                            break
                        }
                        if (!btnArray[i][j].black)
                            valid = 1
                        if (valid == 1 && btnArray[i][j].black) {
                            flag = true
                            break
                        }
                        i--
                        j++
                    }
                    i = olc - 1
                    j = ilc + 1
                    valid = 0
                    //Up Right
                    while (i >= 0 && j < 8 && !flag && btnArray[i][j].clicked) {
                        if (btnArray[i][j].black && valid == 0) {
                            break
                        }
                        if (!btnArray[i][j].black) {
                            valid = 1
                        }
                        if (valid == 1 && btnArray[i][j].black) {
                            flag = true
                            break
                        }
                        i--
                        j++
                    }
                    i = olc + 1
                    j = ilc - 1
                    //Down Left
                    while (i < 8 && j >= 0 && !flag && btnArray[i][j].clicked) {
                        if (btnArray[i][j].black && valid == 0) {
                            break
                        }
                        if (!btnArray[i][j].black)
                            valid = 1
                        if (valid == 1 && btnArray[i][j].black) {
                            flag = true
                            break
                        }
                        i++
                        j--
                    }
                    i = olc - 1
                    j = ilc
                    valid = 0

                    //Up
                    while (i >= 0 && !flag && btnArray[i][j].clicked) {
                        if (btnArray[i][j].black && valid == 0) {
                            break
                        }
                        if (!btnArray[i][j].black)
                            valid = 1
                        if (valid == 1 && btnArray[i][j].black) {
                            flag = true
                            break
                        }
                        i--
                    }
                    i = olc + 1
                    j = ilc
                    valid = 0

                    //Down
                    while (i < 8 && !flag && btnArray[i][j].clicked) {
                        if (btnArray[i][j].black && valid == 0) {
                            break
                        }
                        if (!btnArray[i][j].black)
                            valid = 1
                        if (valid == 1 && btnArray[i][j].black) {
                            flag == true
                            break
                        }
                        i++
                    }
                    i = olc
                    j = ilc - 1
                    valid = 0

                    //Left
                    while (j >= 0 && !flag && btnArray[i][j].clicked) {
                        if (btnArray[i][j].black && valid == 0) {
                            break
                        }
                        if (!btnArray[i][j].black)
                            valid == 1
                        if (valid == 1 && btnArray[i][j].black) {
                            flag == true
                            break
                        }
                        j--
                    }
                    i = olc
                    j = ilc + 1
                    valid = 0


                    //Right
                    while (j < 8 && !flag && btnArray[i][j].clicked) {
                        if (btnArray[i][j].black && valid == 0) {
                            break
                        }
                        if (!btnArray[i][j].black)
                            valid == 1
                        if (valid == 1 && btnArray[i][j].black) {
                            flag = true
                            break
                        }
                        j++
                    }
                } else {
                    i = olc + 1
                    j = ilc + 1
                    flag = false

                    //Diagonal Right Down
                    while(i<8 && j<8 && btnArray[i][j].clicked)
                    {
                        if(!btnArray[i][j].black && valid == 0 )
                        {
                            break
                        }
                        if(btnArray[i][j].black)
                            valid =1
                        if(valid == 1 && !btnArray[i][j].black)
                        {
                            flag = true
                            break
                        }
                        i++
                        j++
                    }

                    i = olc - 1
                    j = ilc - 1
                    valid = 0

                    //Up Left
                    while ()
                }
            }
        }
    }

}
