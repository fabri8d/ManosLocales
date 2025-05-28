package com.undef.ManosLocales.data.repository

import com.undef.ManosLocales.data.local.entities.Seller

interface SellerRepository {
    suspend fun getSellerByUserId(userId: Int): Seller?
    suspend fun insertSeller(seller: Seller)
    suspend fun deleteSellerByUserId(userId: Int)
    suspend fun deleteAllSellers()
}
