package com.undef.ManosLocales.data.repository

import com.undef.ManosLocales.data.local.entities.Product

interface ProductRepository {
    suspend fun getProductById(id: Int): Product?
    suspend fun getProductsByOwnerId(ownerId: Int): List<Product>
    suspend fun insertProduct(product: Product)
    suspend fun deleteProductById(id: Int)
    suspend fun deleteAllProducts()
}
