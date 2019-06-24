package com.example.fakerestapi.user.modal

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo :Geo = Geo(lat = 0,lng = 0)
    )