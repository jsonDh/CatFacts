package com.json.catfacts.data.api

import com.json.catfacts.data.models.CatImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImagesAPI {

    @GET("search")
    suspend fun getImages(
        @Header("x-api-key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("has_breeds") breeds: Int
    ): Response<List<CatImage>>

}