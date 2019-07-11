package com.example.todoappminor

interface ListViewListener {
    fun checkboxClick(task : TasksTable.Task, position : Int)
    fun btnDeleteClick(task: TasksTable.Task,position: Int)
}