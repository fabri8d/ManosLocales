package com.undef.ManosLocales.data.repository

import com.undef.ManosLocales.data.local.entities.Seller
import com.undef.ManosLocales.data.local.entities.User

interface SellerRepository {
    suspend fun getSellerByUserId(userId: Int): Seller?
    suspend fun deleteSellerByUserId(userId: Int)
    suspend fun deleteAllSellers()
    suspend fun registerSeller(userId: Int, businessName: String )
    suspend fun getAllSellers(): List<Seller>
}
