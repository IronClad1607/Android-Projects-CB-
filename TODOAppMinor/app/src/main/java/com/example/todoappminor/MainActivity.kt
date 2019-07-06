package com.example.todoappminor

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var tasks = arrayListOf<TodoTable.Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val dbHelper = MyDbHelper(this)
        val tasksDB = dbHelper.writableDatabase


        tasks = TodoTable.getAllTasks(tasksDB)


        val todoAdapter = TODOAdapter(tasks)
        lvTODO.adapter = todoAdapter


        btnADD.setOnClickListener {
            TodoTable.insertTask(
                tasksDB, TodoTable.Task(
                    null,
                    etTask.text.toString(),
                    false
                )
            )

            tasks = TodoTable.getAllTasks(tasksDB)

            todoAdapter.updateTasks(tasks)
        }

    }


    class TODOAdapter(var tasks: ArrayList<TodoTable.Task>) : BaseAdapter() {

        fun updateTasks(newTasks: ArrayList<TodoTable.Task>) {
            tasks.clear()
            tasks.addAll(newTasks)
            notifyDataSetChanged()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val li = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = li.inflate(R.layout.list_todo, parent, false)

            view.findViewById<TextView>(R.id.tvTask).text = getItem(position).task


            return view
        }

        override fun getItem(position: Int): TodoTable.Task = tasks[position]

        override fun getItemId(position: Int): Long = 0

        override fun getCount(): Int = tasks.size

    }
}
