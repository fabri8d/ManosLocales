package com.undef.ManosLocales.data.remote.models

data class SellerRegisterRequest(
    val userId: Int,
    val rating: Double = 0.0,
    val businessName: String
)
