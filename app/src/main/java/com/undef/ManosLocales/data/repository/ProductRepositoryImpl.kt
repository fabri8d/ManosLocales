package com.undef.ManosLocales.data.repository

import com.undef.ManosLocales.data.local.dao.ProductDao
import com.undef.ManosLocales.data.mapper.toDomain
import com.undef.ManosLocales.data.mapper.toEntity
import com.undef.ManosLocales.data.local.entities.Product
import com.undef.ManosLocales.data.repository.helpers.SellerProvider

class ProductRepositoryImpl(
    private val productDao: ProductDao,
    private val sellerProvider: SellerProvider
) : ProductRepository {

    override suspend fun getProductById(id: Int): Product? {
        val entity = productDao.getProductById(id) ?: return null
        val owner = sellerProvider.getSellerByUserId(entity.ownerId) ?: return null
        return entity.toDomain(owner)
    }

    override suspend fun getProductsByOwnerId(ownerId: Int): List<Product> {
        return productDao.getProductsByOwnerId(ownerId).mapNotNull {
            val owner = sellerProvider.getSellerByUserId(it.ownerId) ?: return@mapNotNull null
            it.toDomain(owner)
        }
    }

    override suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product.toEntity())
    }

    override suspend fun deleteProductById(id: Int) {
        productDao.deleteProductById(id)
    }

    override suspend fun deleteAllProducts() {
        productDao.deleteAllProducts()
    }
}
