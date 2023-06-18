package com.json.catfacts.presentation.di

import com.json.catfacts.data.repository.DBRepositoryImpl
import com.json.catfacts.data.repository.FactsRepositoryImpl
import com.json.catfacts.data.repository.ImagesRepositoryImpl
import com.json.catfacts.domain.repository.CatFactRepository
import com.json.catfacts.domain.repository.DBRepository
import com.json.catfacts.domain.repository.ImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindFactsRepository(factsRepositoryImpl: FactsRepositoryImpl): CatFactRepository

    @Binds
    @ViewModelScoped
    abstract fun bindDbRepositoryImpl(dbRepositoryImpl: DBRepositoryImpl): DBRepository

    @Binds
    @ViewModelScoped
    abstract fun bindImagesRepository(imagesRepositoryImpl: ImagesRepositoryImpl): ImagesRepository

}