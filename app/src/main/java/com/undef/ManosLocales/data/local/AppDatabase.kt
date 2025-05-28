package com.undef.ManosLocales.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.undef.ManosLocales.data.local.dao.ProductDao
import com.undef.ManosLocales.data.local.dao.SellerDao
import com.undef.ManosLocales.data.local.dao.UserDao
import com.undef.ManosLocales.data.local.entities.room.ProductEntity
import com.undef.ManosLocales.data.local.entities.room.SellerEntity
import com.undef.ManosLocales.data.local.entities.room.UserEntity

@Database(
    entities = [UserEntity::class, ProductEntity::class, SellerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun sellerDao(): SellerDao
}
