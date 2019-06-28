package com.example.fakerestapi.allActivities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.modal.Posts
import com.example.fakerestapi.modal.User
import com.example.fakerestapi.network.RetrofitClient
import com.example.fakerestapi.network.getPosts
import com.example.fakerestapi.ui.PostAdapter
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class PostActivity : AppCompatActivity(), CoroutineScope {

    val superviser = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + superviser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        if (intent.getStringExtra("data") == "user") {
            val api = RetrofitClient.postAPI
            val userId = intent.getIntExtra("userId", 0)
            api.getPostByUserId(userId).enqueue(object : Callback<List<Posts>> {
                override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                    Toast.makeText(
                        this@PostActivity, "Network Call Failed!", Toast.LENGTH_LONG
                    ).show()
                }

                override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                    val data = response.body()

                    rvPosts.layoutManager = LinearLayoutManager(this@PostActivity, RecyclerView.VERTICAL, false)
                    rvPosts.adapter = PostAdapter(data!!)
                }

            })
        } else {
            launch {
                val post = getPosts()

                rvPosts.layoutManager = LinearLayoutManager(this@PostActivity, RecyclerView.VERTICAL, false)
                rvPosts.adapter = PostAdapter(post)
            }
        }
    }
}
