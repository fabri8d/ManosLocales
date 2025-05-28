package com.undef.ManosLocales.data.mapper

import com.undef.ManosLocales.data.local.entities.room.ProductEntity
import com.undef.ManosLocales.data.local.entities.Seller
import com.undef.ManosLocales.data.local.entities.Product

// De ProductEntity + Seller a Product (dominio)
fun ProductEntity.toDomain(owner: Seller): Product {
    return Product(
        id = id,
        name = name,
        price = price,
        category = category,
        description = description,
        owner = owner,
        image = image
    )
}

// De Product (dominio) a ProductEntity
fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price,
        category = category,
        description = description,
        ownerId = owner.user.id,
        image = image
    )
}
