package com.json.catfacts.data.repository

import com.json.catfacts.data.api.ImagesAPI
import com.json.catfacts.domain.repository.ImagesRepository
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(private val imagesAPI: ImagesAPI) :
    ImagesRepository {
    override suspend fun getImages(apiKey: String, limit: Int, breeds: Int) =
        imagesAPI.getImages(apiKey, limit, breeds)
}