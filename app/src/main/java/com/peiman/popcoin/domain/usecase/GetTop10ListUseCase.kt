package com.peiman.popcoin.domain.usecase

import com.peiman.popcoin.data.model.list.Top10ListModel
import com.peiman.popcoin.data.model.list.Top10Model
import com.peiman.popcoin.data.util.Resource
import com.peiman.popcoin.domain.repository.ListRepository

class GetTop10ListUseCase(private val listRepository: ListRepository) {
    suspend fun execute(
        apiKey: String,
        vsCurrency: String,
        order: String,
        perPage: Int,
        page: Int,
        sparkline: Boolean
    ): Resource<Top10ListModel> {
        return listRepository.getTop10Coins(
            apiKey,
            vsCurrency,
            order,
            perPage,
            page,
            sparkline
        )
    }
}