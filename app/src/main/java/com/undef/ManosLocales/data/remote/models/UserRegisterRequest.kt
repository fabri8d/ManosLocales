package com.undef.ManosLocales.data.remote.models

data class UserRegisterRequest(
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
