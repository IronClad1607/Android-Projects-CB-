package com.example.fakerestapi.network

import com.example.fakerestapi.modal.Comments
import com.example.fakerestapi.modal.Posts
import com.example.fakerestapi.modal.User
import retrofit2.Response
import retrofit2.http.GET

interface UserAPI {


    @GET("/users")
    suspend fun getUsers(): Response<List<User>>

    @GET("/posts")
    suspend fun getPost(): Response<List<Posts>>

    @GET("/comments")
    suspend fun getComment(): Response<List<Comments>>

//    suspend fun getTODO(): Response<List<TODO>>
}