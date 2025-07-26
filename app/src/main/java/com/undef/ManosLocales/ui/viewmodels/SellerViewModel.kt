package com.undef.ManosLocales.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.ManosLocales.data.repository.SellerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellerViewModel @Inject constructor(
    private val repository: SellerRepository
) : ViewModel() {

    fun registerSeller(
        userId: Int,
        businessName: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                repository.registerSeller(userId, businessName)
                onSuccess()
            } catch (e: Exception) {
                onError("Error al registrar vendedor: ${e.message}")
            }
        }
    }
}
