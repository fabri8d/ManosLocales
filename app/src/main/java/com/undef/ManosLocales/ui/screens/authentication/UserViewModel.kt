package com.undef.ManosLocales.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.ManosLocales.data.local.entities.User
import com.undef.ManosLocales.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError: StateFlow<String?> = _loginError


    private val _registeredUser = MutableStateFlow<User?>(null)
    val registeredUser: StateFlow<User?> = _registeredUser


    fun login(username: String, password: String) {
        viewModelScope.launch {
            val isValid = userRepository.validateUserCredentials(username, password)
            if (isValid) {
                _isAuthenticated.value = true
                _loginError.value = null
                val user = userRepository.getUserByUsername(username)
                _currentUser.value = user
            } else {
                _isAuthenticated.value = false
                _loginError.value = "Email o contraseña incorrectos, ${username}, $password"
                _currentUser.value = null
            }
        }
    }

    fun register(user: User) {

        viewModelScope.launch {
            try {
                val newUser = userRepository.saveUserWithHashedPassword(user)
                _registeredUser.value = newUser
            } catch (e: Exception) {
                // Manejo de errores si lo necesitas
            }
        }
    }

    fun logout() {
        userRepository.clearSession()
        _isAuthenticated.value = false
        _currentUser.value = null
    }

    fun saveSession(token: String) {
        userRepository.saveAuthToken(token)
    }

    fun loadSession() {
        val token = userRepository.getAuthToken()
        _isAuthenticated.value = token != null
    }
}
