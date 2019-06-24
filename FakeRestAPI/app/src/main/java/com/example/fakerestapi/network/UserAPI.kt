package com.example.fakerestapi.network

import com.example.fakerestapi.modal.User
import retrofit2.Response
import retrofit2.http.GET

interface UserAPI {


    @GET("/users")
    suspend fun getUsers(): Response<List<User>>
}