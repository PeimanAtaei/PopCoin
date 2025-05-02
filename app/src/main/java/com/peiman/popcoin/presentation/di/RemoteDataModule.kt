package com.example.popcoin.presentation.di

import com.peiman.popcoin.data.api.CoingeckoService
import com.peiman.popcoin.data.repository.dataSource.RemoteDataSource
import com.peiman.popcoin.data.repository.dataSourceImpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(coingeckoService: CoingeckoService) : RemoteDataSource {
        return RemoteDataSourceImpl(coingeckoService)
    }
}