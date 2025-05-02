package com.peiman.popcoin.data.model.chart

data class PriceChartModel(
    val market_caps: List<List<Double>>,
    val prices: List<List<Double>>,
    val total_volumes: List<List<Double>>
)