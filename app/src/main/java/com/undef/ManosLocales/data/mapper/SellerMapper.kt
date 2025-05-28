package com.undef.ManosLocales.data.mapper

import com.undef.ManosLocales.data.local.entities.room.SellerEntity
import com.undef.ManosLocales.data.local.entities.User
import com.undef.ManosLocales.data.local.entities.Seller

// De SellerEntity + User a Seller (dominio)
fun SellerEntity.toDomain(user: User): Seller {
    return Seller(
        user = user,
        rating = rating,
        businessName = businessName
    )
}

// De Seller (dominio) a SellerEntity
fun Seller.toEntity(): SellerEntity {
    return SellerEntity(
        userId = user.id,
        rating = rating,
        businessName = businessName
    )
}
