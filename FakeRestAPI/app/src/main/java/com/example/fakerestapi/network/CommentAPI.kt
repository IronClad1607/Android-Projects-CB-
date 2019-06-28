package com.example.fakerestapi.network

import com.example.fakerestapi.modal.Comments
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentAPI {

    @GET("/comments")
    suspend fun getComments():Response<List<Comments>>

    @GET("/comments")
    fun getCommentsPerPost(@Query("postId")postId:Int):Call<List<Comments>>
}