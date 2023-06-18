package com.json.catfacts.data.models

import com.google.gson.annotations.SerializedName
import com.json.catfacts.data.entities.CatFact

data class CatFactList(
    @SerializedName("current_page") val page: Int,
    @SerializedName("data") val catFacts: List<CatFact>,
    @SerializedName("first_page_url") val firstPageUrl: String,
    @SerializedName("from") val from: Int,
    @SerializedName("last_page") val lastPage: Int,
    @SerializedName("last_page_url") val lastPageUrl: String,
    @SerializedName("links") val links: List<Links>,
    @SerializedName("next_page_url") val nextPageUrl: String?,
    @SerializedName("path") val path: String,
    @SerializedName("per_page") val perPage: String?,
    @SerializedName("to") val to: Int,
    @SerializedName("total") val total: Int

)