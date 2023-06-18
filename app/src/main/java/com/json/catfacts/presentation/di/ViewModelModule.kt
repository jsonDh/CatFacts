package com.json.catfacts.presentation.di

import android.content.Context
import com.json.catfacts.presentation.viewmodels.CatFactsViewModel
import com.json.catfacts.utils.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @Singleton
    fun providesCatFactsViewModel(): CatFactsViewModel {
        return CatFactsViewModel()
    }

    @Provides
    @Singleton
    fun providesAppPreferences(@ApplicationContext appContext: Context): AppPreferences =
        AppPreferences(appContext)


}