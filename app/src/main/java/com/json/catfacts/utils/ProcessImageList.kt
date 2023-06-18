package com.json.catfacts.utils

import com.json.catfacts.data.entities.CatFact
import com.json.catfacts.data.models.CatImage

object ProcessImageList {

    fun processList(catFacts: List<CatFact>, catImages: List<CatImage>) =
        catFacts.mapIndexed { index, catFact ->
            val catImage = catImages[index]
            catFact.copy(url = catImage.url)
        }

    fun processImage(catFact: CatFact, catImages: List<CatImage>): CatFact {
        catImages.forEach { image ->
            catFact.url = image.url
        }
        return catFact
    }
}