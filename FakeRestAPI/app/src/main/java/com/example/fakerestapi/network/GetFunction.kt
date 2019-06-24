package com.example.fakerestapi.network

import com.example.fakerestapi.modal.Posts
import com.example.fakerestapi.modal.User

suspend fun getPosts(): List<Posts> {
    val userApi = RetrofitClient.userApi

    val responseP = userApi.getPost()
    return if (responseP.isSuccessful) {
        responseP.body()!!
    } else {
        emptyList()
    }
}

suspend fun getUsers(): List<User> {
    val userApi = RetrofitClient.userApi

    val responseU = userApi.getUsers()
    return if (responseU.isSuccessful) {
        responseU.body()!!
    } else {
        emptyList()
    }
}