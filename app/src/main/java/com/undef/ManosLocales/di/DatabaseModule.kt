package com.undef.ManosLocales.di

import android.content.Context
import androidx.room.Room
import com.undef.ManosLocales.data.local.AppDatabase
import com.undef.ManosLocales.data.local.dao.ProductDao
import com.undef.ManosLocales.data.local.dao.SellerDao
import com.undef.ManosLocales.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "manos_locales_db"
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }
    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }

    @Provides
    fun provideSellerDao(appDatabase: AppDatabase): SellerDao {
        return appDatabase.sellerDao()
    }

}
