package com.peiman.popcoin.data.repository.dataSourceImpl

import com.peiman.popcoin.data.api.CoingeckoService
import com.peiman.popcoin.data.model.chart.PriceChartModel
import com.peiman.popcoin.data.model.detail.DetailModel
import com.peiman.popcoin.data.model.list.Top10ListModel
import com.peiman.popcoin.data.model.list.Top10Model
import com.peiman.popcoin.data.repository.dataSource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(private val coingeckoService: CoingeckoService) : RemoteDataSource {

    override suspend fun getTop10Coins(
        apiKey: String,
        vsCurrency: String,
        order: String,
        perPage: Int,
        page: Int,
        sparkline: Boolean
    ): Response<Top10ListModel> {
        return coingeckoService.getTop10List(apiKey, vsCurrency, order, perPage, page, sparkline)
    }

    override suspend fun getCoinDetail(key: String, coinId: String): Response<DetailModel> {
        return coingeckoService.getDetail(key, coinId)
    }

    override suspend fun getPriceChart(
        apiKey: String, coinId: String, vsCurrency: String, days: Int, interval: String
    ): Response<PriceChartModel> {
        return coingeckoService.getPriceChart(
            apiKey, coinId, vsCurrency, days, interval
        )
    }
}