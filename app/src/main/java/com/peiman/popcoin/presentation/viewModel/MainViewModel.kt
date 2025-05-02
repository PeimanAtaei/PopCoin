package com.peiman.popcoin.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.peiman.popcoin.BuildConfig
import com.peiman.popcoin.data.model.chart.PriceChartModel
import com.peiman.popcoin.data.model.detail.DetailModel
import com.peiman.popcoin.data.model.list.Top10ListModel
import com.peiman.popcoin.data.model.list.Top10Model
import com.peiman.popcoin.data.util.Resource
import com.peiman.popcoin.domain.usecase.GetCoinDetailUseCase
import com.peiman.popcoin.domain.usecase.GetPriceChartUseCase
import com.peiman.popcoin.domain.usecase.GetTop10ListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * MainViewModel is responsible for managing the data used by the main activity and its associated fragments.
 * It interacts with the domain layer (use cases) to fetch data like the top 10 cryptocurrencies,
 * the details of a specific coin, and price chart data.
 */
class MainViewModel(
    private val app: Application,
    private val getTop10ListUseCase: GetTop10ListUseCase,
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    private val getPriceChartUseCase: GetPriceChartUseCase
) : AndroidViewModel(app) {

    companion object {
        private const val TAG = "MainViewModel"
    }

    // LiveData to hold data and notify the UI when it changes
    val top10List: MutableLiveData<Resource<Top10ListModel>> = MutableLiveData()
    val coinDetail: MutableLiveData<Resource<DetailModel>> = MutableLiveData()
    val priceChart : MutableLiveData<Resource<PriceChartModel>> = MutableLiveData()

    // === main methods to fetch data ============================================================

    /**
     * Fetches the top 10 cryptocurrencies from the server and updates the top10List LiveData.
     * This method checks if there's an active internet connection and makes the appropriate network request.
     *
     * @param vs_currency The currency in which the prices are returned (e.g., "usd", "eur").
     * @param order The sorting order for the list (e.g., "market_cap_desc" for descending market cap).
     * @param per_page Number of items per page.
     * @param page The page number to fetch.
     * @param sparkline If true, include price sparkline data.
     */
    fun getTop10List(
        vs_currency: String,
        order: String,
        per_page: Int,
        page: Int,
        sparkline: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Set the LiveData to Loading state
                top10List.postValue(Resource.Loading())

                // Check if internet is available before making the network request
                if (isInternetAvailable(app)) {

                    // Call the use case to fetch the top 10 list
                    val result = getTop10ListUseCase.execute(
                        BuildConfig.API_KEY,
                        vs_currency,
                        order,
                        per_page,
                        page,
                        sparkline
                    )
                    // Update the LiveData with the result
                    top10List.postValue(result)
                } else {
                    Toast.makeText(app, "No Internet Connection!!!", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Log.e(TAG, "getTop10List: ${e.message}")
            }
        }
    }


    /**
     * Fetches the details of a specific coin by its ID and updates the coinDetail LiveData.
     * This method checks if there's an active internet connection and makes the appropriate network request.
     *
     * @param id The ID of the coin to fetch details for (e.g., "bitcoin", "ethereum").
     */
    fun getDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Set the LiveData to Loading state
                coinDetail.postValue(Resource.Loading())

                // Check if internet is available before making the network request
                if (isInternetAvailable(app)) {
                    // Call the use case to fetch the coin details
                    val result = getCoinDetailUseCase.execute(
                        id,
                        BuildConfig.API_KEY// API key
                    )
                    // Update the LiveData with the result
                    coinDetail.postValue(result)
                } else {
                    Toast.makeText(app, "No Internet Connection!!!", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Log.e(TAG, "coinDetail: ${e.message}")
            }
        }
    }


    /**
     * Fetches the price chart data for a specific coin over a given period and interval.
     * This method checks if there's an active internet connection and makes the appropriate network request.
     *
     * @param coinId The ID of the coin to fetch the price chart data for (e.g., "bitcoin").
     * @param vs_currency The currency in which the prices are returned (e.g., "usd").
     * @param days The number of days for which to fetch the price data.
     * @param interval The interval of the price data (e.g., "daily", "hourly").
     */
    fun getPriceChart(coinId:String, vs_currency: String, days: Int, interval: String) {

        viewModelScope.launch(Dispatchers.IO) {

            try {

                // Set the LiveData to Loading state
                priceChart.postValue(Resource.Loading())

                // Check if internet is available before making the network request
                if (isInternetAvailable(app)) {

                    // Call the use case to fetch the price chart data
                    val result = getPriceChartUseCase.execute(
                        coinId,
                        BuildConfig.API_KEY,
                        vs_currency,
                        days,
                        interval
                    )
                    // Update the LiveData with the result
                    priceChart.postValue(result)
                } else {
                    Toast.makeText(app, "No Internet Connection!!!", Toast.LENGTH_SHORT).show()
                }

            }catch (e: Exception){
                Log.e(TAG, "getPriceChart: ${e.message}")
            }

        }

    }


    // === other methods ===========================================================================

    /**
     * Checks if the device has an active internet connection.
     * This method supports both modern (API >= 23) and older (pre-API 23) Android versions.
     *
     * @param context The application context used to access the ConnectivityManager.
     * @return Boolean indicating whether the device has internet connectivity.
     */
    @Suppress("DEPRECATION")
    private fun isInternetAvailable(context: Context): Boolean {
        var result = false

        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cm?.run {
                    cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                        result = when {
                            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                            else -> false
                        }
                    }
                }
            } else {
                cm?.run {
                    cm.activeNetworkInfo?.run {
                        if (type == ConnectivityManager.TYPE_WIFI) {
                            result = true
                        } else if (type == ConnectivityManager.TYPE_MOBILE) {
                            result = true
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "isInternetAvailable: ${e.message}")
        }

        return result
    }

}