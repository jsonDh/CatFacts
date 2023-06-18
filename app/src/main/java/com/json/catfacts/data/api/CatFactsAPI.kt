package com.json.catfacts.data.api

import com.json.catfacts.data.entities.CatFact
import com.json.catfacts.data.models.CatFactList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactsAPI {

    @GET("fact")
    suspend fun getFact(): Response<CatFact>

    @GET("facts")
    suspend fun getListOfCatFacts(@Query("limit") limit: Int): Response<CatFactList>

}