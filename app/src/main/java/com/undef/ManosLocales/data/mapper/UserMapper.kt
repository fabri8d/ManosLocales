package com.undef.ManosLocales.data.mapper

import com.undef.ManosLocales.R
import com.undef.ManosLocales.data.local.entities.Product
import com.undef.ManosLocales.data.local.entities.User
import com.undef.ManosLocales.data.local.entities.room.UserEntity
import com.undef.ManosLocales.data.remote.models.UserDto

// De UserEntity a User (ya lo tenés)
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

// 🆕 De UserDto (API) a User (modelo local)
fun UserDto.toDomain(): User {
    return User(
        id = id,
        name = firstName,
        surname = lastName,
        dateOfBirth = "", // Si después el backend lo incluye, se actualiza
        username = username,
        email = email,
        password = password,
        city = "", // Lo mismo
        favoriteProducts = mutableListOf(),
        image = R.drawable.usuario // recurso de imagen por defecto
    )
}
