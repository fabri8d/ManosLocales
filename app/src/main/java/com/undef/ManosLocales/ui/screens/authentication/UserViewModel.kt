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

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val isValid = userRepository.validateUserCredentials(username, password)
            _isAuthenticated.value = isValid
            if (isValid) {
                val user = userRepository.getUserByUsername(username)
                _currentUser.value = user
            } else {
                _currentUser.value = null
            }
        }
    }

    fun register(user: User) {
        viewModelScope.launch {
            userRepository.saveUserWithHashedPassword(user)
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
