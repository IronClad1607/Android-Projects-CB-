package com.example.fakerestapi.allActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.network.getPosts
import com.example.fakerestapi.network.getComments
import com.example.fakerestapi.ui.CommentsAdapter
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CommentsActivity : AppCompatActivity(), CoroutineScope {

    val superviser = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + superviser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        launch {
            val post = getPosts()
            val comment = getComments()

            rvComments.layoutManager = LinearLayoutManager(
                this@CommentsActivity,
                RecyclerView.VERTICAL,
                false
            )

            rvComments.adapter = CommentsAdapter(comment,post)
        }
    }
}
