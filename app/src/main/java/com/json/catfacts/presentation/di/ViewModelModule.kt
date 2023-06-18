package com.json.catfacts.presentation.di

import com.json.catfacts.presentation.viewmodels.CatFactsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @Singleton
    fun providesCatFactsViewModel() : CatFactsViewModel {
        return CatFactsViewModel()
    }

}