package com.json.catfacts.data.models

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("url") val url: String?,
    @SerializedName("label") val label: String,
    @SerializedName("active") val active: Boolean
)