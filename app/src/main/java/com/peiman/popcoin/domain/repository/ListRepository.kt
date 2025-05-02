package com.peiman.popcoin.domain.repository

import com.peiman.popcoin.data.model.list.Top10ListModel
import com.peiman.popcoin.data.model.list.Top10Model
import com.peiman.popcoin.data.util.Resource

interface ListRepository {

    suspend fun getTop10Coins(
        apiKey: String,
        vsCurrency: String,
        order: String,
        perPage: Int,
        page: Int,
        sparkline: Boolean
    ): Resource<Top10ListModel>

}