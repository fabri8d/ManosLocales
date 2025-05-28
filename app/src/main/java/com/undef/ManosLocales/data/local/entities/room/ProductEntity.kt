package com.undef.ManosLocales.data.local.entities.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val category: String,
    val description: String,
    val ownerId: Int, // Clave foránea al Seller
    val image: Int
)
