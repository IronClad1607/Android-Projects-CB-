package com.example.fakerestapi.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.modal.Posts
import com.example.fakerestapi.modal.User
import kotlinx.android.synthetic.main.cvposts.view.*

class PostAdapter(private val post: List<Posts>, private val user: List<User>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.cvposts, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount() = post.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = post[position]
        val user = user[position % 10]
        holder.bind(post, user)
        Log.i("CCheck", "$position")

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Posts, user: User) {
            with(itemView) {
                tvUsernameP.text = user.username
                tvTitle.text = post.title
                tvBody.text = post.body
            }
        }
    }
}