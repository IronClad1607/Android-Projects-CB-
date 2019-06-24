package com.example.fakerestapi.user.modal

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address = Address(street = "", suite = "",city = "",zipcode = "",geo = Geo(lat = 0,lng = 0))
)