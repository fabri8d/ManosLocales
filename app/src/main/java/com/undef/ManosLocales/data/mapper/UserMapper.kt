package com.undef.ManosLocales.data.mapper

import com.undef.ManosLocales.data.local.entities.Product
import com.undef.ManosLocales.data.local.entities.User
import com.undef.ManosLocales.data.local.entities.room.UserEntity

// De UserEntity a User
fun UserEntity.toDomain(favoriteProducts: MutableList<Product> = mutableListOf()): User {
    return User(
        id = id,
        name = name,
        surname = surname,
        dateOfBirth = dateOfBirth,
        username = username,
        email = email,
        password = password,
        city = city,
        favoriteProducts = favoriteProducts,
        image = image
    )
}

// De User a UserEntity
fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        surname = surname,
        dateOfBirth = dateOfBirth,
        username = username,
        email = email,
        password = password,
        city = city,
        image = image
    )
}
