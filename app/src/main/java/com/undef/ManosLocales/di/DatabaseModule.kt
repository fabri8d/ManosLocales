package com.undef.ManosLocales.di

import android.content.Context
import androidx.room.Room
import com.undef.ManosLocales.data.local.AppDatabase
import com.undef.ManosLocales.data.local.dao.ProductDao
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
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }


}
