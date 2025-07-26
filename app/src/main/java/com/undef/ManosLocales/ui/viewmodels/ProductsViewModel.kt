package com.undef.ManosLocales.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.ManosLocales.data.local.entities.Product
import com.undef.ManosLocales.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    fun loadProductsByOwner(ownerId: Int) {
        viewModelScope.launch {
            _products.value = repository.getProductsByOwnerId(ownerId)
        }
    }

    fun insertProduct(product: Product) {
        viewModelScope.launch {
            repository.insertProduct(product)
            loadProductsByOwner(product.owner.user.id) // actualiza lista
        }
    }

    fun deleteProduct(id: Int, ownerId: Int) {
        viewModelScope.launch {
            repository.deleteProductById(id)
            loadProductsByOwner(ownerId)
        }
    }
}