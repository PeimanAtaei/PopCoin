package com.peiman.popcoin.data.model.detail

data class MarketData(
    val current_price: CurrentPrice,
    val high_24h: High24h,
    val low_24h: Low24h,
    val total_supply: Double,
)