package com.undef.ManosLocales.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.ManosLocales.data.local.entities.Product
import com.undef.ManosLocales.data.local.entities.Seller
import com.undef.ManosLocales.data.repository.ProductRepository
import com.undef.ManosLocales.data.repository.SellerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val sellerRepository: SellerRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _sellers = MutableStateFlow<List<Seller>>(emptyList())
    val sellers: StateFlow<List<Seller>> = _sellers

    fun loadSellers() {
        viewModelScope.launch {
            try {
                val sellersFromApi = sellerRepository.getAllSellers()
                _sellers.value = sellersFromApi
            } catch (e: Exception) {
                println("Error cargando vendedores: ${e.message}")
            }
        }
    }

    fun loadProducts(ownerId: Int) {
        viewModelScope.launch {
            _products.value = productRepository.getProductsByOwnerId(ownerId)
        }
    }
}
