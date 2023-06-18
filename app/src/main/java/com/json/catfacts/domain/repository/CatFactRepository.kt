package com.json.catfacts.domain.repository

import com.json.catfacts.data.entities.CatFact
import com.json.catfacts.data.models.CatFactList
import retrofit2.Response

interface CatFactRepository {

    suspend fun getCatFact() : Response<CatFact>
    suspend fun getListOfCatFacts(limit : Int) : Response<CatFactList>
}