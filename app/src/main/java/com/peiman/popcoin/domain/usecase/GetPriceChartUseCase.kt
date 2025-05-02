package com.peiman.popcoin.domain.usecase

import com.peiman.popcoin.data.model.chart.PriceChartModel
import com.peiman.popcoin.data.model.detail.DetailModel
import com.peiman.popcoin.data.model.list.Top10ListModel
import com.peiman.popcoin.data.util.Resource
import com.peiman.popcoin.domain.repository.DetailRepository

class GetPriceChartUseCase(private val detailRepository: DetailRepository) {

    suspend fun execute(
        apiKey: String,
        coinId: String,
        vsCurrency: String,
        days: Int,
        interval: String
    ): Resource<PriceChartModel> {
        return detailRepository.getPriceChart(
            apiKey,
            coinId,
            vsCurrency,
            days,
            interval
        )
    }
}