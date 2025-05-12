package com.undef.ManosLocales.entities

import android.media.Image

data class User(
    val id: Int,
    val name: String,
    val surname: String,
    val dateOfBirth: String,
    val username: String,
    val email: String,
    val password: String,
    val city: String,
    var favoriteProducts: MutableList<Product>,
    val image: Int
)
