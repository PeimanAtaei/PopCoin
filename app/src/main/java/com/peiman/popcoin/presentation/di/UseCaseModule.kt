package com.example.popcoin.presentation.di

import com.peiman.popcoin.domain.repository.DetailRepository
import com.peiman.popcoin.domain.repository.ListRepository
import com.peiman.popcoin.domain.usecase.GetCoinDetailUseCase
import com.peiman.popcoin.domain.usecase.GetPriceChartUseCase
import com.peiman.popcoin.domain.usecase.GetTop10ListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetTop10ListUseCase(listRepository: ListRepository): GetTop10ListUseCase {

        return GetTop10ListUseCase(listRepository)
    }

    @Singleton
    @Provides
    fun provideGetCoinDetailUseCase(detailRepository: DetailRepository): GetCoinDetailUseCase {

        return GetCoinDetailUseCase(detailRepository)
    }

    @Singleton
    @Provides
    fun provideGetPriceChartUseCase(detailRepository: DetailRepository): GetPriceChartUseCase {

        return GetPriceChartUseCase(detailRepository)
    }

}