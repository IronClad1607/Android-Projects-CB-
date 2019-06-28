package com.example.fakerestapi.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.allActivities.CommentsActivity
import com.example.fakerestapi.modal.Posts
import kotlinx.android.synthetic.main.cvposts.view.*

class PostAdapter(private val post: List<Posts>,context: Context) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    val mContext = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.cvposts, parent, false)
        return ViewHolder(view,mContext)
    }


    override fun getItemCount() = post.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = post[position]
        holder.bind(post)
        Log.i("CCheck", "${post.userId}")

    }

    class ViewHolder(itemView: View,context: Context) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Posts) {
            with(itemView) {
                tvTitle.text = post.title
                tvBody.text = post.body
                setOnClickListener {
                    val intent = Intent(context,CommentsActivity::class.java)
                    intent.putExtra("data","post")
                    intent.putExtra("postId",post.id)
                    startActivity(context,intent,null)
                }
            }
        }
    }
}