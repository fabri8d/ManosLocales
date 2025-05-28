package com.undef.ManosLocales.data.repository.helpers

import com.undef.ManosLocales.data.repository.SellerRepository

class SellerProviderImpl(
    private val sellerRepository: SellerRepository
) : SellerProvider {
    override suspend fun getSellerByUserId(userId: Int) = sellerRepository.getSellerByUserId(userId)
}
