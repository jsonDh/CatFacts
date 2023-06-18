package com.json.catfacts.presentation.viewmodels

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.json.catfacts.data.entities.CatFact
import com.json.catfacts.domain.repository.DBRepository
import com.json.catfacts.utils.SwipeToDeleteCallbackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DBCatFactsViewModel @Inject constructor(
    private val catFactsViewModel: CatFactsViewModel,
    private val dbRepository: DBRepository
) : ViewModel(), SwipeToDeleteCallbackListener {

    companion object {
        private const val TAG = "DB_CAT-FACT_VM"
    }

    private var job: Job? = null

    fun saveFact() {
        storeCatFact()
    }

    fun getStoredFacts() {
        getStoredCatFacts()
    }

    private fun storeCatFact() {
        catFactsViewModel.loading.value = true
        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                dbRepository.saveCatFact(catFactsViewModel.catFact.value!!)
                    .collect {
                        withContext(Dispatchers.Main){
                            getStoredCatFacts()
                            catFactsViewModel.loading.value = false
                        }
                    }
            } catch (e: SQLiteConstraintException) {
                withContext(Dispatchers.Main) {
                    catFactsViewModel.loading.value = false
                    catFactsViewModel.errorMessage.value =
                        "You already have that Cat Fact stored but don't worry tomorrow you'll get a new one!"
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.localizedMessage?.let { Log.e(TAG, it) }
                    catFactsViewModel.loading.value = false
                }
            }
        }
    }

    private fun deleteCatFact(catFact: CatFact){
        catFactsViewModel.loading.value = true
        job = viewModelScope.launch(Dispatchers.IO) {
            dbRepository.deleteCatFact(catFact)
                .catch { e ->
                    Log.e(TAG, e.message.toString())
                }
                .collect{
                    withContext(Dispatchers.Main){
                        getStoredCatFacts()
                        catFactsViewModel.loading.value = false
                    }
                }
        }
    }

    private fun getStoredCatFacts() {
        job = viewModelScope.launch(Dispatchers.IO) {
            dbRepository.getAllFacts()
                .catch { e ->
                    Log.e(TAG, e.message.toString())
                }
                .collect {
                    withContext(Dispatchers.Main) {
                        catFactsViewModel.catFactList.postValue(it)
                    }
                }
        }
    }

    override fun onItemSwiped(position: Int, catFact: CatFact) {
        deleteCatFact(catFact)
    }

}