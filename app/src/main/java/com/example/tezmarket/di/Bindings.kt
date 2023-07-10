package com.example.tezmarket.di

import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.repositoryimpl.FavoriteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.builders.ActivityComponentBuilder
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object Bindings {
    @Provides
    @Singleton
    fun provideFavRepo(
        tezMarketApi: TezMarketApi
    ): FavoriteRepositoryImpl = FavoriteRepositoryImpl(tezMarketApi)

}