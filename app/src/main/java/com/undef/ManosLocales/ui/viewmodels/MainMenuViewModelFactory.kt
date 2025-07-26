package com.undef.ManosLocales.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.undef.ManosLocales.data.repository.ProductRepository
import com.undef.ManosLocales.data.repository.SellerRepository

class MainMenuViewModelFactory(
    private val productRepository: ProductRepository,
    private val sellerRepository: SellerRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainMenuViewModel::class.java)) {
            return MainMenuViewModel(productRepository, sellerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
