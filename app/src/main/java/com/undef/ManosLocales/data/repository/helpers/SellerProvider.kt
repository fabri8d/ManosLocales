package com.undef.ManosLocales.data.repository.helpers

import com.undef.ManosLocales.data.local.entities.Seller

interface SellerProvider {
    suspend fun getSellerByUserId(userId: Int): Seller?
}