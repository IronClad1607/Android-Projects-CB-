package com.example.fakerestapi.allActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.modal.Posts
import com.example.fakerestapi.modal.User
import com.example.fakerestapi.network.RetrofitClient
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
            val post = getPosts()
            val user = getUsers()
            Log.i("PCheck", "$post")
            rvPosts.layoutManager = LinearLayoutManager(this@PostActivity, RecyclerView.VERTICAL, false)
            rvPosts.adapter = PostAdapter(post,user)
        }
    }

    suspend fun getPosts(): List<Posts> {
        val userApi = RetrofitClient.userApi

        val responseP = userApi.getPost()
        return if (responseP.isSuccessful) {
            responseP.body()!!
        } else {
            emptyList()
        }
    }

    suspend fun getUsers(): List<User> {
        val userApi = RetrofitClient.userApi

        val responseU = userApi.getUsers()
        return if (responseU.isSuccessful) {
            responseU.body()!!
        } else {
            emptyList()
        }
    }
}
