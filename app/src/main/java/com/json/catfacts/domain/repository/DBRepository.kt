package com.json.catfacts.domain.repository

import com.json.catfacts.data.entities.CatFact
import kotlinx.coroutines.flow.Flow

interface DBRepository {

    suspend fun getAllFacts(): Flow<List<CatFact>>

    suspend fun saveCatFact(catFact: CatFact) : Flow<Unit>

    suspend fun deleteCatFact(catFact: CatFact) : Flow<Unit>

}