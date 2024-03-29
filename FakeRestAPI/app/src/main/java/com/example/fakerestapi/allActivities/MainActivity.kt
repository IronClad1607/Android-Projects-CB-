package com.example.fakerestapi.allActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fakerestapi.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnUSERS.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        btnPOSTS.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }

        btnComments.setOnClickListener {
            val intent = Intent(this, CommentsActivity::class.java)
            startActivity(intent)
        }

        btnTODO.setOnClickListener {
            val intent = Intent(this,TODOActivity::class.java)
            startActivity(intent)
        }
    }
}
