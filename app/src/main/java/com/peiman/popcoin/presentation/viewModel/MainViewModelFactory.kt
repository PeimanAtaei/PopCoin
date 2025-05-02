package com.peiman.popcoin.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peiman.popcoin.domain.usecase.GetCoinDetailUseCase
import com.peiman.popcoin.domain.usecase.GetPriceChartUseCase
import com.peiman.popcoin.domain.usecase.GetTop10ListUseCase

class MainViewModelFactory(
    private val app: Application,
    private val getTop10ListUseCase: GetTop10ListUseCase,
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    private val getPriceChartUseCase: GetPriceChartUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            app,
            getTop10ListUseCase,
            getCoinDetailUseCase,
            getPriceChartUseCase
        ) as T
    }

}