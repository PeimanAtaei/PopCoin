package com.example.popcoin.presentation.di

import com.peiman.popcoin.presentation.adapter.Top10ListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
class AdapterModule {

    @Provides
    fun providerTop10ListAdapter():Top10ListAdapter{
        return Top10ListAdapter()
    }

}