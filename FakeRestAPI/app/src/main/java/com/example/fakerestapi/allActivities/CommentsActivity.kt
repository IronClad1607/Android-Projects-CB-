package com.example.fakerestapi.allActivities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.modal.Comments
import com.example.fakerestapi.network.RetrofitClient
import com.example.fakerestapi.network.getComments
import com.example.fakerestapi.network.getPosts
import com.example.fakerestapi.ui.CommentsAdapter
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class CommentsActivity : AppCompatActivity(), CoroutineScope {

    val superviser = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + superviser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        if (intent.getStringExtra("data") == "post") {
            val api = RetrofitClient.commentApi
            val postId = intent.getIntExtra("postId", 0)
            api.getCommentsPerPost(postId).enqueue(object : Callback<List<Comments>> {
                override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                    Toast.makeText(this@CommentsActivity, "Network call Failed!", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                    val data = response.body()
                    rvComments.layoutManager = LinearLayoutManager(
                        this@CommentsActivity,
                        RecyclerView.VERTICAL,
                        false
                    )

                    rvComments.adapter = CommentsAdapter(data!!)
                }

            })

        } else {
            launch {

                val comment = getComments()

                rvComments.layoutManager = LinearLayoutManager(
                    this@CommentsActivity,
                    RecyclerView.VERTICAL,
                    false
                )

                rvComments.adapter = CommentsAdapter(comment)
            }
        }
    }
}
