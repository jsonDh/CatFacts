package com.json.catfacts.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.json.catfacts.BuildConfig
import com.json.catfacts.data.entities.CatFact
import com.json.catfacts.domain.repository.CatFactRepository
import com.json.catfacts.domain.repository.ImagesRepository
import com.json.catfacts.utils.ProcessImageList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class APICatFactViewModel @Inject constructor(
    private val catFactsViewModel: CatFactsViewModel,
    private val factsRepository: CatFactRepository,
    private val imagesRepository: ImagesRepository
) : ViewModel() {

    companion object {
        private const val TAG = "API_CAT-FACT_VM"
    }

    private var job: Job? = null

    fun getFact() {
        getCatFactOTD()
    }

    private fun getCatFactOTD() {
        catFactsViewModel.loading.value = true
        job = viewModelScope.launch(Dispatchers.IO) {
            val result = factsRepository.getCatFact()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    getRandomImage(result.body()!!)
                } else {
                    catFactsViewModel.loading.value = false
                }
            }
        }
    }

    private fun getRandomImage(newCatFact: CatFact) {
        job = viewModelScope.launch(Dispatchers.IO) {
            val result = imagesRepository.getImages(BuildConfig.IMAGES_API_KEY, 1, 1)
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    val catFact: CatFact =
                        ProcessImageList.processImage(newCatFact, result.body()!!)
                    catFactsViewModel.storeNewCatFact(catFact)
                }
                catFactsViewModel.loading.value = false
            }
        }
    }

}