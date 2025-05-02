package com.peiman.popcoin.domain.repository

import com.peiman.popcoin.data.model.chart.PriceChartModel
import com.peiman.popcoin.data.model.detail.DetailModel
import com.peiman.popcoin.data.util.Resource

interface DetailRepository {

    suspend fun getCoinDetail(key: String, coinId: String): Resource<DetailModel>

    suspend fun getPriceChart(
        apiKey: String,
        coinId: String,
        vsCurrency: String,
        days: Int,
        interval: String
    ): Resource<PriceChartModel>
}