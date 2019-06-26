package com.example.fakerestapi.modal

data class Comments(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)