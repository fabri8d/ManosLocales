package com.undef.ManosLocales.data.repository

import com.undef.ManosLocales.data.local.dao.SellerDao
import com.undef.ManosLocales.data.local.dao.UserDao
import com.undef.ManosLocales.data.mapper.toDomain
import com.undef.ManosLocales.data.mapper.toEntity
import com.undef.ManosLocales.data.local.entities.Seller

class SellerRepositoryImpl(
    private val sellerDao: SellerDao,
    private val userDao: UserDao
) : SellerRepository {

    override suspend fun getSellerByUserId(userId: Int): Seller? {
        val sellerEntity = sellerDao.getSellerByUserId(userId) ?: return null
        val userEntity = userDao.getUserById(userId) ?: return null
        return sellerEntity.toDomain(userEntity.toDomain())
    }

    override suspend fun insertSeller(seller: Seller) {
        sellerDao.insertSeller(seller.toEntity())
    }

    override suspend fun deleteSellerByUserId(userId: Int) {
        sellerDao.deleteSellerByUserId(userId)
    }

    override suspend fun deleteAllSellers() {
        sellerDao.deleteAllSellers()
    }
}
