package com.peiman.popcoin.data.model.detail

data class DetailModel(
    val description: Description,
    val id: String,
    val image: Image,
    val links: Links,
    val market_data: MarketData,
    val name: String,
    val symbol: String,
)