package com.undef.ManosLocales.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.undef.ManosLocales.data.local.entities.User
import com.undef.ManosLocales.data.mapper.toDomain
import com.undef.ManosLocales.data.remote.ApiService
import com.undef.ManosLocales.data.remote.models.UserLoginRequest
import com.undef.ManosLocales.data.remote.models.UserRegisterRequest
import com.google.gson.Gson
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val prefs: SharedPreferences,
    private val apiService: ApiService
) : UserRepository {

    private val gson = Gson()

    private val AUTH_TOKEN_KEY = "AUTH_TOKEN"
    private val IS_LOGGED_IN_KEY = "IS_LOGGED_IN"
    private val USER_JSON_KEY = "USER_JSON"

    override fun getAuthToken(): String? = prefs.getString(AUTH_TOKEN_KEY, null)

    override fun saveAuthToken(token: String) {
        prefs.edit {
            putString(AUTH_TOKEN_KEY, token)
            putBoolean(IS_LOGGED_IN_KEY, true)
        }
    }

    override fun clearSession() {
        prefs.edit {
            remove(AUTH_TOKEN_KEY)
            remove(USER_JSON_KEY)
            putBoolean(IS_LOGGED_IN_KEY, false)
        }
    }

    override suspend fun loginRemote(username: String, password: String): User? {
        return try {
            val request = UserLoginRequest(username, password)
            val response = apiService.login(request)
            if (response.isSuccessful) {
                val dto = response.body()
                val token = dto?.token
                val user = dto?.user?.toDomain()

                if (token != null && user != null) {
                    saveAuthToken(token)
                    saveUser(user)       // Guardar usuario en prefs
                    user
                } else null
            } else null
        } catch (e: Exception) {
            null
        }
    }

    override fun saveUser(user: User) {
        val json = gson.toJson(user)
        prefs.edit().putString(USER_JSON_KEY, json).apply()
    }

    override fun getUser(): User? {
        val json = prefs.getString(USER_JSON_KEY, null) ?: return null
        return gson.fromJson(json, User::class.java)
    }

    override suspend fun registerUser(request: UserRegisterRequest): Boolean {
        return try {
            val response = apiService.registerUser(request)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun fetchRemoteUsers(): List<User> {
        return try {
            val response = apiService.getAllUsers()
            if (response.isSuccessful) {
                response.body()?.map { it.toDomain() } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
