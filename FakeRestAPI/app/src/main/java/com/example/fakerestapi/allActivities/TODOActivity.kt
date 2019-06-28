package com.example.fakerestapi.allActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.network.getTODOs
import com.example.fakerestapi.network.getUsers
import com.example.fakerestapi.ui.TODOAdapter
import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TODOActivity : AppCompatActivity(), CoroutineScope {

    val superviser = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + superviser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        launch {
            val todo = getTODOs()
            val user = getUsers()

            rvTODO.layoutManager = GridLayoutManager(
                this@TODOActivity,
                5,
                GridLayoutManager.HORIZONTAL,
                false
            )

            rvTODO.adapter = TODOAdapter(todo, user)
        }
    }
}
