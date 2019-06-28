package com.example.fakerestapi.network

import com.example.fakerestapi.modal.Comments
import com.example.fakerestapi.modal.Posts
import com.example.fakerestapi.modal.TodoClass
import com.example.fakerestapi.modal.User

suspend fun getPosts(): List<Posts> {
    val postApi = RetrofitClient.postAPI

    val responseP = postApi.getPost()
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

suspend fun getComments(): List<Comments> {
    val userApi = RetrofitClient.commentApi

    val responseC = userApi.getComments()
    return if (responseC.isSuccessful) {
        responseC.body()!!
    } else {
        emptyList()
    }
}

suspend fun getTODOs(): List<TodoClass> {
    val userApi = RetrofitClient.userApi
    val responseT = userApi.getTODO()
    return if (responseT.isSuccessful) {
        responseT.body()!!
    } else {
        emptyList()
    }
}

