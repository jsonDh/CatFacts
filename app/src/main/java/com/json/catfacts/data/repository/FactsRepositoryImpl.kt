package com.json.catfacts.data.repository

import com.json.catfacts.data.api.CatFactsAPI
import com.json.catfacts.domain.repository.CatFactRepository
import javax.inject.Inject

class FactsRepositoryImpl @Inject constructor(private val catFactsAPI: CatFactsAPI) : CatFactRepository {
    override suspend fun getCatFact() = catFactsAPI.getFact()
    override suspend fun getListOfCatFacts(limit : Int) = catFactsAPI.getListOfCatFacts(limit)
}