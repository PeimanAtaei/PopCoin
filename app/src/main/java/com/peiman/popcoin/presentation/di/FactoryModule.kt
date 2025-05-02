package com.example.popcoin.presentation.di

import android.app.Application
import com.peiman.popcoin.domain.usecase.GetCoinDetailUseCase
import com.peiman.popcoin.domain.usecase.GetPriceChartUseCase
import com.peiman.popcoin.domain.usecase.GetTop10ListUseCase
import com.peiman.popcoin.presentation.viewModel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FactoryModule {

    @Singleton
    @Provides
    fun provideMainViewModelFactoryModule(
        app: Application,
        getTop10ListUseCase: GetTop10ListUseCase,
        getCoinDetailUseCase: GetCoinDetailUseCase,
        getPriceChartUseCase: GetPriceChartUseCase
    ): MainViewModelFactory{
        return MainViewModelFactory(
            app,
            getTop10ListUseCase,
            getCoinDetailUseCase,
            getPriceChartUseCase
        )
    }

}