package com.example.fakerestapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    val userApi = retrofit().create(UserAPI::class.java)

    val postAPI = retrofit().create(PostAPI::class.java)

    val commentApi = retrofit().create(CommentAPI::class.java)

    private fun retrofit()=Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}