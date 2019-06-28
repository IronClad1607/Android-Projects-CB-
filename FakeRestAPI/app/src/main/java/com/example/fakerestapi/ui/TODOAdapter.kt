package com.example.fakerestapi.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.modal.TodoClass
import com.example.fakerestapi.modal.User
import com.example.fakerestapi.network.getTODOs
import kotlinx.android.synthetic.main.cvtodo.view.*

class TODOAdapter(private val todos: List<TodoClass>, private val users: List<User>) :
    RecyclerView.Adapter<TODOAdapter.ViewHolder>() {
    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val todo = todos[position]
        val user = users[position / 20]

        holder.bind(todo, user)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.cvtodo, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: TodoClass, user: User) {
            with(itemView) {
                tvName.text = user.name
                tvTitle.text = todo.title
                if (todo.completed) {
                    tvCompleted.setBackgroundColor(Color.RED)
                } else {
                    tvCompleted.setBackgroundColor(Color.BLACK)
                }
            }
        }
    }
}