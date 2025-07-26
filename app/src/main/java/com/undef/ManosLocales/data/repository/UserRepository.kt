package com.undef.ManosLocales.data.repository

import com.undef.ManosLocales.data.local.entities.User
import com.undef.ManosLocales.data.remote.models.UserRegisterRequest



interface UserRepository {
    fun getAuthToken(): String?
    fun saveAuthToken(token: String)
    fun clearSession()

    suspend fun loginRemote(username: String, password: String): User?
    suspend fun registerUser(request: UserRegisterRequest): Boolean
    suspend fun fetchRemoteUsers(): List<User>

    // Nuevos métodos para persistir usuario en prefs
    fun saveUser(user: User)
    fun getUser(): User?
}

