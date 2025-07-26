package com.undef.ManosLocales.data.remote.models

data class UserLoginRequest(
    val username: String,
    val password: String
)
data class LoginResponse(
    val token: String,
    val user: UserDto
)