package com.json.catfacts.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.json.catfacts.data.entities.CatFact
import javax.inject.Inject

class CatFactsViewModel @Inject constructor(
) : ViewModel() {

    val catFact = MutableLiveData<CatFact>()
    val catFactList = MutableLiveData<List<CatFact>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    companion object {
        private const val TAG = "CAT-FACT_VM"
    }

    fun storeNewCatFact(newCatFact: CatFact) {
        this.catFact.value = newCatFact
    }

}