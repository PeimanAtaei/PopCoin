package com.peiman.popcoin.domain.usecase

import com.peiman.popcoin.data.model.detail.DetailModel
import com.peiman.popcoin.data.util.Resource
import com.peiman.popcoin.domain.repository.DetailRepository

class GetCoinDetailUseCase(private val detailRepository: DetailRepository) {

    suspend fun execute(key:String,coinId:String): Resource<DetailModel>{
        return detailRepository.getCoinDetail(key,coinId)
    }

}