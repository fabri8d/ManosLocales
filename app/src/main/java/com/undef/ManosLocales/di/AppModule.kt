package com.undef.ManosLocales.di

import android.content.Context
import android.content.SharedPreferences
import com.undef.ManosLocales.data.local.dao.ProductDao
import com.undef.ManosLocales.data.local.dao.SellerDao
import com.undef.ManosLocales.data.local.dao.UserDao
import com.undef.ManosLocales.data.repository.ProductRepository
import com.undef.ManosLocales.data.repository.ProductRepositoryImpl
import com.undef.ManosLocales.data.repository.SellerRepository
import com.undef.ManosLocales.data.repository.SellerRepositoryImpl
import com.undef.ManosLocales.data.repository.UserRepository
import com.undef.ManosLocales.data.repository.UserRepositoryImpl
import com.undef.ManosLocales.data.repository.helpers.SellerProvider
import com.undef.ManosLocales.data.repository.helpers.SellerProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao,
        sharedPreferences: SharedPreferences
    ): UserRepository {
        return UserRepositoryImpl(userDao, sharedPreferences)
    }
    @Provides
    @Singleton
    fun provideSellerRepository(
        sellerDao: SellerDao,
        userDao: UserDao
    ): SellerRepository {
        return SellerRepositoryImpl(sellerDao, userDao)
    }

    @Provides
    @Singleton
    fun provideSellerProvider(
        sellerRepository: SellerRepository
    ): SellerProvider {
        return SellerProviderImpl(sellerRepository)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        productDao: ProductDao,
        sellerProvider: SellerProvider
    ): ProductRepository {
        return ProductRepositoryImpl(productDao, sellerProvider)
    }

}
