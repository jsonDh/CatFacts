package com.json.catfacts.data.repository

import com.json.catfacts.data.db.AppDataBase
import com.json.catfacts.data.entities.CatFact
import com.json.catfacts.domain.repository.DBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DBRepositoryImpl @Inject constructor(private val appDataBase: AppDataBase) :
    DBRepository {

    override suspend fun getAllFacts(): Flow<List<CatFact>> = flow {
        emit(appDataBase.catFactDao().getAllFacts())
    }

    override suspend fun saveCatFact(catFact: CatFact): Flow<Unit> = flow {
        appDataBase.catFactDao().saveCatFact(catFact)
        emit(Unit)
    }

    override suspend fun deleteCatFact(catFact: CatFact) = flow {
        appDataBase.catFactDao().deleteCatFact(catFact)
        emit(Unit)
    }

}