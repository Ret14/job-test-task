package com.ntpro.mobileandroiddevtestwork.di

import android.content.Context
import androidx.room.Room
import com.ntpro.mobileandroiddevtestwork.data.local.DealStore
import com.ntpro.mobileandroiddevtestwork.data.local.DealStoreImpl
import com.ntpro.mobileandroiddevtestwork.data.room.DealsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomerDatabase(@ApplicationContext context: Context): DealsDatabase {
        return Room
            .databaseBuilder(context, DealsDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDealStore(database: DealsDatabase): DealStore {
        return DealStoreImpl(database)
    }

//    @Singleton
//    @Provides
//    fun provideDealMediator(store: DealStore): DealRemoteMediator {
//        return DealRemoteMediator(store)
//    }

}