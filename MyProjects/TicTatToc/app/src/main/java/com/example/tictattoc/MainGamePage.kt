package com.example.tictattoc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_game_page.*

class MainGamePage : AppCompatActivity(), View.OnClickListener {

    val player1 = ArrayList<Int>()
    val player2 = ArrayList<Int>()
    var winner = -1


    var activePlayer = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game_page)
        attachListeners()
    }

    private fun attachListeners() {
        val btnArray = arrayOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        for (btn in btnArray) {
            btn.setOnClickListener(this)
        }
    }

    override fun onClick(p0: View?) {
        var cellID = 0

        if (p0 != null) {
            when (p0.id) {
                R.id.btn1 -> cellID = 1
                R.id.btn2 -> cellID = 2
                R.id.btn3 -> cellID = 3
                R.id.btn4 -> cellID = 4
                R.id.btn5 -> cellID = 5
                R.id.btn6 -> cellID = 6
                R.id.btn7 -> cellID = 7
                R.id.btn8 -> cellID = 8
                R.id.btn9 -> cellID = 9
            }
        }
        process(cellID, p0 as Button)
    }

    private fun process(cellID: Int, btnSelected: Button) {
        if (activePlayer == 1) {
            btnSelected.text = "X"
            btnSelected.setBackgroundColor(Color.parseColor("#009193"))
            player1.add(cellID)
            activePlayer = 2
        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundColor(Color.parseColor("#FF9300"))
            player2.add(cellID)
            activePlayer = 1
        }

        btnSelected.isEnabled = false
        checkForWinner()
    }


    private fun checkForWinner() {


        //rows
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }

        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //cols
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }

        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //diagonals
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }

        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (winner != -1) {
            if (winner == 1) {
                Toast.makeText(this, " Player 1 wins the Game!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Player 2 wins the Game!", Toast.LENGTH_LONG).show()
            }
            resetGame()
        }


    }

//    private fun draw() {
//        if (winner == -1) {
//            Toast.makeText(this, "Game is Drawn!", Toast.LENGTH_LONG).show()
//        }
//    }L̥

    private fun resetGame() {
        player1.clear()
        player2.clear()
        val btnArray = arrayOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        for (btn in btnArray) {
            btn.isEnabled = false
        }
    }

}
