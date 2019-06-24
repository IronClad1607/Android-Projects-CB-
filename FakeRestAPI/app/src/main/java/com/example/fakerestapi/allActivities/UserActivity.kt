package com.example.fakerestapi.allActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fakerestapi.R
import com.example.fakerestapi.modal.User
import com.example.fakerestapi.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserActivity : AppCompatActivity(),CoroutineScope {

    val superviser = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + superviser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        launch {
            val users = getUser()
            Log.i("PUI","$users")
        }

    }

    suspend fun getUser(): List<User> {
        val userApi = RetrofitClient.userApi

        val response = userApi.getUsers()

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            emptyList()
        }
    }
}
