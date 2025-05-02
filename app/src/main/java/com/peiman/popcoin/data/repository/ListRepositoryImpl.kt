package com.peiman.popcoin.data.repository

import com.peiman.popcoin.data.model.list.Top10ListModel
import com.peiman.popcoin.data.model.list.Top10Model
import com.peiman.popcoin.data.repository.dataSource.RemoteDataSource
import com.peiman.popcoin.data.util.Resource
import com.peiman.popcoin.domain.repository.ListRepository
import retrofit2.Response

class ListRepositoryImpl(private val remoteDataSource: RemoteDataSource) : ListRepository {
    override suspend fun getTop10Coins(
        apiKey: String,
        vsCurrency: String,
        order: String,
        perPage: Int,
        page: Int,
        sparkline: Boolean
    ): Resource<Top10ListModel> {
        return listResponseToResource(
            remoteDataSource.getTop10Coins(
                apiKey,
                vsCurrency,
                order,
                perPage,
                page,
                sparkline
            )
        )
    }


    // convert response to resource ================================================================

    private fun listResponseToResource(response: Response<Top10ListModel>): Resource<Top10ListModel> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


}