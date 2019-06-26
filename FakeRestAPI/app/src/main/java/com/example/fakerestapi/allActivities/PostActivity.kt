package com.example.fakerestapi.allActivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.network.getPosts
import com.example.fakerestapi.network.getUsers
import com.example.fakerestapi.ui.PostAdapter
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PostActivity : AppCompatActivity(), CoroutineScope {

    val superviser = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + superviser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        launch {
            val user = getUsers()
            val post = getPosts()

            rvPosts.layoutManager = LinearLayoutManager(this@PostActivity, RecyclerView.VERTICAL, false)
            rvPosts.adapter = PostAdapter(post,user)
        }
    }


}
