package com.example.todoappminor

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class TasksTable {

    data class Task(
        val id: Int?,
        val task: String,
        var done: Boolean
    )

    companion object {

        val TABLE_NAME = "tasks"

        val CMD_CREATE_TABLE = """
            CREATE TABLE $TABLE_NAME (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            task TEXT,
            done BOOLEAN
            );
        """.trimIndent()


        fun insertTasks(db: SQLiteDatabase, task: Task) {
            val taskRow = ContentValues()

            taskRow.put("task", task.task)
            taskRow.put("done", task.done)

            db.insert(TABLE_NAME, null, taskRow)
        }

        fun deleteCrossTask(db: SQLiteDatabase, task: Task) {
            val taskRow = ContentValues()

            taskRow.put("task", task.task)
            taskRow.put("done", task.done)

            db.delete(TABLE_NAME, "id = ?", arrayOf(task.id.toString()))
        }


        fun deleteDoneTask(db: SQLiteDatabase) {
            db.delete(TABLE_NAME, "done = 1", null)
        }

        fun sortTask(db: SQLiteDatabase): ArrayList<Task> {
            val tasks = ArrayList<Task>()


            val cursor = db.query(
                TABLE_NAME,
                arrayOf("id", "task", "done"),
                null,
                null,
                null,
                null,
                "done"
            )

            cursor.moveToFirst()


            val idCol = cursor.getColumnIndex("id")
            val taskCol = cursor.getColumnIndex("task")
            val doneCol = cursor.getColumnIndex("done")


            while (cursor.moveToNext()) {
                val task = Task(
                    cursor.getInt(idCol),
                    cursor.getString(taskCol),
                    cursor.getInt(doneCol) == 1
                )


                tasks.add(task)
            }

            cursor.close()
            return tasks
        }

        fun searchQuery(db: SQLiteDatabase, search: String): ArrayList<Task> {
            val tasks = ArrayList<Task>()

            val cursor = db.query(
                TABLE_NAME,
                arrayOf("id", "task", "done"),
                "task LIKE '%search%'",
                null,
                null,
                null,
                null
            )

            cursor.moveToFirst()


            val idCol = cursor.getColumnIndex("id")
            val taskCol = cursor.getColumnIndex("task")
            val doneCol = cursor.getColumnIndex("done")


            while (cursor.moveToNext()) {
                val task = Task(
                    cursor.getInt(idCol),
                    cursor.getString(taskCol),
                    cursor.getInt(doneCol) == 1
                )


                tasks.add(task)
            }

            cursor.close()
            return tasks
        }

        fun updateTask(db: SQLiteDatabase, task: Task) {

            val taskRow = ContentValues()
            taskRow.put("task", task.task)
            taskRow.put("done", task.done)

            db.update(TABLE_NAME, taskRow, "id = ?", arrayOf(task.id.toString()))
        }

        fun getAllTasks(db: SQLiteDatabase): ArrayList<Task> {
            val tasks = ArrayList<Task>()

            val cursor = db.query(
                TABLE_NAME,
                arrayOf("id", "task", "done"),
                null,
                null,
                null,
                null,
                null
            )

            cursor.moveToFirst()


            val idCol = cursor.getColumnIndex("id")
            val taskCol = cursor.getColumnIndex("task")
            val doneCol = cursor.getColumnIndex("done")


            while (cursor.moveToNext()) {
                val task = Task(
                    cursor.getInt(idCol),
                    cursor.getString(taskCol),
                    cursor.getInt(doneCol) == 1
                )


                tasks.add(task)
            }

            cursor.close()
            return tasks
        }
    }
}