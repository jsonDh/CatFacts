package com.json.catfacts.presentation.di

import android.content.Context
import androidx.room.Room
import com.json.catfacts.data.api.CatFactsAPI
import com.json.catfacts.data.api.ImagesAPI
import com.json.catfacts.data.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    private val FACTS_URL = "https://catfact.ninja/"
    private val IMAGES_URL = " https://api.thecatapi.com/v1/images/"
    private val DB_NAME = "saved-cat-facts"

    @Provides
    @ViewModelScoped
    fun providesFactsAPI(): CatFactsAPI = Retrofit.Builder().baseUrl(FACTS_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).build().create(CatFactsAPI::class.java)

    @Provides
    @ViewModelScoped
    fun providesImagesAPI(): ImagesAPI =
        Retrofit.Builder().baseUrl(IMAGES_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(ImagesAPI::class.java)

    @Provides
    @ViewModelScoped
    fun providesDBHelper(@ApplicationContext appContext: Context): AppDataBase =
        Room.databaseBuilder(appContext, AppDataBase::class.java, DB_NAME)
            .build()

}