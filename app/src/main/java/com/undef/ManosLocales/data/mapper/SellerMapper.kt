package com.undef.ManosLocales.data.mapper

import com.undef.ManosLocales.data.local.entities.Seller
import com.undef.ManosLocales.data.local.entities.User
import com.undef.ManosLocales.data.remote.models.SellerDto
import com.undef.ManosLocales.data.remote.models.UserDto

// Mapea SellerDto + UserDto a Seller (modelo dominio)
fun SellerDto.toDomain(userDto: UserDto): Seller {
    return Seller(
        user = userDto.toDomain(),
        rating = rating,
        businessName = businessName
    )
}

// Opcional: si necesitas, de Seller a SellerDto para enviar al backend
fun Seller.toDto(): SellerDto {
    return SellerDto(
        userId = user.id,
        rating = rating,
        businessName = businessName
    )
}
