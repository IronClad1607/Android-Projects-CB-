package com.example.fakerestapi.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.modal.Comments
import com.example.fakerestapi.modal.Posts
import kotlinx.android.synthetic.main.cvcomments.view.*

class CommentsAdapter(private val comments: List<Comments>) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.cvcomments, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val comment = comments[position]

        holder.bind(comment)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comments: Comments) {
            with(itemView) {

                tvName.text = comments.name
                tvEmail.text = comments.email
                tvBody.text = comments.body
            }
        }
    }
}