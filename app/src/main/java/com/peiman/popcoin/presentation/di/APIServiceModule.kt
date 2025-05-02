package com.example.popcoin.presentation.di

import com.peiman.popcoin.data.api.CoingeckoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class APIServiceModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client : OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()


        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.coingecko.com/api/")
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideCoinGeckoService(retrofit: Retrofit): CoingeckoService {

        return retrofit.create(CoingeckoService::class.java)

    }

}