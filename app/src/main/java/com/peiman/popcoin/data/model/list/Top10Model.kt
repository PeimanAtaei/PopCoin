package com.peiman.popcoin.data.model.list

data class Top10Model(

    val circulating_supply: Double,
    val current_price: Double,
    val high_24h: Double,
    val id: String,
    val image: String,
    val last_updated: String,
    val low_24h: Double,
    val market_cap: Long,
    val market_cap_rank: Int,
    val max_supply: Double,
    val name: String,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val symbol: String,
    val total_supply: Double,
    val total_volume: Long
)