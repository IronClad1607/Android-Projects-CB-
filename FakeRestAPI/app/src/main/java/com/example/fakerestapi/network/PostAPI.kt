package com.example.fakerestapi.network

import com.example.fakerestapi.modal.Posts
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostAPI {

    @GET("/posts")
    suspend fun getPost(): Response<List<Posts>>

    @GET("/posts")
    fun getPostByUserId(@Query("userId") userId :Int): Call<List<Posts>>
}