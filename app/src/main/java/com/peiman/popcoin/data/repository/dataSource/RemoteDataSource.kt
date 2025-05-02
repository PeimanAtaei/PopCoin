package com.peiman.popcoin.data.repository.dataSource

import com.peiman.popcoin.data.model.chart.PriceChartModel
import com.peiman.popcoin.data.model.detail.DetailModel
import com.peiman.popcoin.data.model.list.Top10ListModel
import com.peiman.popcoin.data.model.list.Top10Model
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getTop10Coins(
        apiKey: String,
        vsCurrency: String,
        order: String,
        perPage: Int,
        page: Int,
        sparkline: Boolean
    ): Response<Top10ListModel>

    suspend fun getCoinDetail(key:String ,coinId:String): Response<DetailModel>

    suspend fun getPriceChart(
        apiKey: String,
        coinId: String,
        vsCurrency: String,
        days: Int,
        interval: String
    ): Response<PriceChartModel>

}