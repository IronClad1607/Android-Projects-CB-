package com.example.todoappminor

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_todo.view.*
import android.widget.CheckBox as CheckBox

class MainActivity : AppCompatActivity() {

    var tasks = arrayListOf<TasksTable.Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val dbHelper = MyDbHelper(this)
        val tasksDB = dbHelper.writableDatabase


        tasks = TasksTable.getAllTasks(tasksDB)


        val todoAdapter = TODOAdapter(tasks,tasksDB)
        lvTODO.adapter = todoAdapter


        btnADD.setOnClickListener {
            TasksTable.insertTasks(
                tasksDB, TasksTable.Task(
                    null,
                    etTask.text.toString(),
                    false
                )
            )

            tasks = TasksTable.getAllTasks(tasksDB)
            todoAdapter.updateTasks(tasks)

            etTask.text.clear()
        }

        btnSort.setOnClickListener {
            tasks = TasksTable.sortTask(tasksDB)
            todoAdapter.updateTasks(tasks)
        }


        btnDelete.setOnClickListener {
            TasksTable.deleteDoneTask(tasksDB)
            val newTaskList = TasksTable.getAllTasks(tasksDB)
            todoAdapter.updateTasks(newTaskList)
        }


        etSearch.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newTaskList = TasksTable.searchQuery(tasksDB,p0.toString())
                todoAdapter.updateTasks(newTaskList)
            }

        })

    }


    class TODOAdapter(var tasks: ArrayList<TasksTable.Task>, private val db:SQLiteDatabase) : BaseAdapter() {

        fun updateTasks(newTasks: ArrayList<TasksTable.Task>) {
            tasks.clear()
            tasks.addAll(newTasks)
            notifyDataSetChanged()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val li = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = convertView ?: li.inflate(R.layout.list_todo, parent, false)

            view.findViewById<TextView>(R.id.tvTask).text = getItem(position).task

            if (getItem(position).done) {
                view.findViewById<TextView>(R.id.tvTask).setTextColor(Color.RED)
                view.findViewById<CheckBox>(R.id.cbDone).isChecked = true
            } else {
                view.findViewById<TextView>(R.id.tvTask).setTextColor(Color.BLACK)
                view.findViewById<CheckBox>(R.id.cbDone).isChecked = false
            }

            view.findViewById<Button>(R.id.btnCross).setOnClickListener {
                val thisTask = getItem(position)
                TasksTable.deleteCrossTask(db, thisTask)
                val newTaskList = TasksTable.getAllTasks(db)
                updateTasks(newTaskList)
            }

            view.findViewById<CheckBox>(R.id.cbDone).setOnClickListener {
                val thisTask = getItem(position)
                thisTask.done = !thisTask.done
                TasksTable.updateTask(db, thisTask)
                val newTaskList = TasksTable.getAllTasks(db)
                updateTasks(newTaskList)
            }
            return view
        }

        override fun getItem(position: Int): TasksTable.Task = tasks[position]

        override fun getItemId(position: Int): Long = 0

        override fun getCount(): Int = tasks.size

    }
}
