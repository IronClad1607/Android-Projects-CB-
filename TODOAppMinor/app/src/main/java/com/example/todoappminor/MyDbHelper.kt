package com.example.todoappminor

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DB_VER = 1
const val DB_NAME = "todo.db"

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VER) {
    override fun onCreate(p0: SQLiteDatabase?) {

        p0?.apply {
            execSQL(TodoTable.CMD_CREATE_TABLE)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}