package com.example.popcoin.presentation.di

import com.peiman.popcoin.data.repository.DetailRepositoryImpl
import com.peiman.popcoin.data.repository.ListRepositoryImpl
import com.peiman.popcoin.data.repository.dataSource.RemoteDataSource
import com.peiman.popcoin.domain.repository.DetailRepository
import com.peiman.popcoin.domain.repository.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideListRepository(remoteDataSource: RemoteDataSource): ListRepository {
        return ListRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideDetailRepository(remoteDataSource: RemoteDataSource): DetailRepository {
        return DetailRepositoryImpl(remoteDataSource)
    }

}