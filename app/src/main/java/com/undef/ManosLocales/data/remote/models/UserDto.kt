package com.undef.ManosLocales.data.remote.models

data class UserDto(
    val id: Int,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val role: String,
    val enabled: Boolean,
    val resetCode: String?,
    val resetCodeExpires: String?
)
