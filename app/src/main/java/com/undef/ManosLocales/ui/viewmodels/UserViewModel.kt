package com.undef.ManosLocales.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.ManosLocales.data.local.entities.User
import com.undef.ManosLocales.data.remote.models.UserRegisterRequest
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

    private val _emailAlreadyUsed = MutableStateFlow(false)
    val emailAlreadyUsed: StateFlow<Boolean> = _emailAlreadyUsed

    private val _loginErrorFlag = MutableStateFlow(false)
    val loginErrorFlag: StateFlow<Boolean> = _loginErrorFlag


    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val user = userRepository.loginRemote(username, password)
                if (user != null) {
                    _currentUser.value = user
                    _isAuthenticated.value = true
                } else {
                    _currentUser.value = null
                    _isAuthenticated.value = false
                    _loginError.value = "Nombre de usuario o contraseña incorrectos"
                }
            } catch (e: Exception) {
                _currentUser.value = null
                _isAuthenticated.value = false
                _loginError.value = "Error al intentar iniciar sesión: ${e.localizedMessage}"
            }
        }
    }

    fun loadSession() {
        val user = userRepository.getUser()
        if (user != null) {
            _currentUser.value = user
            _isAuthenticated.value = true
        } else {
            _currentUser.value = null
            _isAuthenticated.value = false
        }
    }


    fun logout() {
        userRepository.clearSession()
        _isAuthenticated.value = false
        _currentUser.value = null
    }


    fun resetEmailAlreadyUsedFlag() {
        _emailAlreadyUsed.value = false
    }

    fun clearLoginError() {
        _loginError.value = null
    }
    fun registerUserRemote(request: UserRegisterRequest) {
        viewModelScope.launch {
            try {
                val success = userRepository.registerUser(request)

                if (success) {
                    // Si querés, podés transformar el DTO a un User local (aunque por ahora no tenés city ni dateOfBirth en el backend)
                    _registeredUser.value = User(
                        id = 0,
                        username = request.username,
                        email = request.email,
                        password = request.password,
                        city = "", // o lo que decidas más adelante
                        dateOfBirth = "",
                        name = request.firstName,
                        surname = request.lastName,
                        image = 1,
                        favoriteProducts = null
                    )
                    _emailAlreadyUsed.value = false
                } else {
                    _registeredUser.value = null
                    _emailAlreadyUsed.value = true
                }
            } catch (e: Exception) {
                _registeredUser.value = null
                _emailAlreadyUsed.value = true
            }
        }
    }

}