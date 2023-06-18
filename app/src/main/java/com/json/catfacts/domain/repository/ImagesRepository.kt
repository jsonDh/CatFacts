package com.json.catfacts.domain.repository

import com.json.catfacts.data.models.CatImage
import retrofit2.Response

interface ImagesRepository {

    suspend fun getImages(apiKey: String, limit: Int, breeds: Int) : Response<List<CatImage>>

}