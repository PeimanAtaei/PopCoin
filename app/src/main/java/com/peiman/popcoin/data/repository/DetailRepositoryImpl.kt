package com.peiman.popcoin.data.repository

import com.peiman.popcoin.data.model.chart.PriceChartModel
import com.peiman.popcoin.data.model.detail.DetailModel
import com.peiman.popcoin.data.repository.dataSource.RemoteDataSource
import com.peiman.popcoin.data.util.Resource
import com.peiman.popcoin.domain.repository.DetailRepository
import retrofit2.Response

class DetailRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailRepository {

    override suspend fun getCoinDetail(key: String, coinId: String): Resource<DetailModel> {
        return detailResponseToResource(remoteDataSource.getCoinDetail(key, coinId))
    }

    override suspend fun getPriceChart(
        apiKey: String,
        coinId: String,
        vsCurrency: String,
        days: Int,
        interval: String
    ): Resource<PriceChartModel> {
        return priceChartResponseToResource(
            remoteDataSource.getPriceChart(
                apiKey,
                coinId,
                vsCurrency,
                days,
                interval
            )
        )
    }


    // convert response to resource ================================================================

    private fun detailResponseToResource(response: Response<DetailModel>): Resource<DetailModel> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun priceChartResponseToResource(response: Response<PriceChartModel>): Resource<PriceChartModel> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


}