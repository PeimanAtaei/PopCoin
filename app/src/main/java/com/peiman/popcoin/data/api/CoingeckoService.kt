package com.peiman.popcoin.data.api

import com.peiman.popcoin.data.model.chart.PriceChartModel
import com.peiman.popcoin.data.model.detail.DetailModel
import com.peiman.popcoin.data.model.list.Top10ListModel
import com.peiman.popcoin.data.model.list.Top10Model
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoingeckoService {

    @GET(value = "/api/v3/coins/markets")
    suspend fun getTop10List(
        @Query("x_cg_demo_api_key") apiKey: String,
        @Query("vs_currency") vsCurrency: String,
        @Query("order") order: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("sparkline") sparkline: Boolean
    ): Response<Top10ListModel>


    @GET(value = "/api/v3/coins/{coinId}")
    suspend fun getDetail(
        @Path("coinId") coinId: String,
        @Query("x_cg_demo_api_key") key: String
    ): Response<DetailModel>

    @GET(value = "/api/v3/coins/{coinId}/market_chart")
    suspend fun getPriceChart(
        @Path("coinId") coinId: String,
        @Query("x_cg_demo_api_key") apiKey: String,
        @Query("vs_currency") vsCurrency: String,
        @Query("days") days: Int,
        @Query("interval") interval: String
    ): Response<PriceChartModel>

}