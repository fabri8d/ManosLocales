package com.undef.ManosLocales

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: String,
    val description: String,
    val owner: User
)
