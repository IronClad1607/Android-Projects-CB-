package com.example.todoappminor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var tasks = arrayListOf<TasksTable.Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val dbHelper = MyDbHelper(this)
        val tasksDB = dbHelper.writableDatabase


        tasks = TasksTable.getAllTasks(tasksDB)


        val todoAdapter = TODOAdapter(tasks)
        todoAdapter.listItemClickListener = object :ListViewListener{
            override fun btnDeleteClick(task: TasksTable.Task, position: Int) {
                TasksTable.deleteCrossTask(tasksDB, task)
                val newTaskList = TasksTable.getAllTasks(tasksDB)
                todoAdapter.updateTasks(newTaskList)
            }

            override fun checkboxClick(task: TasksTable.Task, position: Int) {
                task.done = !task.done
                TasksTable.updateTask(tasksDB, task)
                val newTaskList = TasksTable.getAllTasks(tasksDB)
                todoAdapter.updateTasks(newTaskList)            }

        }
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



}
