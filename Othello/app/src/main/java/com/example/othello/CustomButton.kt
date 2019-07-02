package com.example.othello

import android.content.Context
import android.widget.Button

class CustomButton(context: Context) : Button(context) {
    var black: Boolean = false
    var atX: Int = 0
    var atY: Int = 0
    var clicked: Boolean = false
    var isCanClicked: Boolean = false

    init {
        black = false
        clicked = false
        isCanClicked = false
    }

    fun setX(x: Int) {
        this.atX = x
    }

    fun setY(y: Int) {
        this.atY = y
    }

}