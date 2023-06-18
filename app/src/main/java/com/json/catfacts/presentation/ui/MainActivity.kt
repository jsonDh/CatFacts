package com.json.catfacts.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.json.catfacts.R
import com.json.catfacts.databinding.ActivityMainBinding
import com.json.catfacts.presentation.ui.adapters.CatFactAdapter
import com.json.catfacts.presentation.viewmodels.APICatFactViewModel
import com.json.catfacts.presentation.viewmodels.CatFactsViewModel
import com.json.catfacts.presentation.viewmodels.DBCatFactsViewModel
import com.json.catfacts.utils.SwipeToDeleteCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var catFactsViewModel: CatFactsViewModel
    private val apiCatFactViewModel: APICatFactViewModel by viewModels()
    private val dbCatFactViewModel: DBCatFactsViewModel by viewModels()
    private var catsFactsAdapter = CatFactAdapter()
    private lateinit var binding: ActivityMainBinding
    private lateinit var swipeToDeleteCallback: SwipeToDeleteCallback

    private lateinit var bottomSheet: BottomSheetBehavior<FrameLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCatFactOTD()
        getStoredFacts()
        showErrorMessages()

        bottomSheet = BottomSheetBehavior.from(binding.bottomSheet).apply {
            peekHeight = 190
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        catFactsViewModel.loading.observe(this) { show ->
            when {
                show -> {
                    binding.progress.visibility = View.VISIBLE
                }

                else -> {
                    binding.progress.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun getStoredFacts() {
        dbCatFactViewModel.getStoredFacts()
        displayCatFactsList()
    }

    private fun getCatFactOTD() {
        apiCatFactViewModel.getFact()
        catFactsViewModel.catFact.observe(this) { newFact ->
            bottomSheet.apply {
                peekHeight = 0
                this.state = BottomSheetBehavior.STATE_EXPANDED
                peekHeight = 190
            }
            binding.newCatFact.text = newFact.fact.toString()
            Glide.with(this@MainActivity).load(newFact.url)
                .placeholder(R.drawable.paw)
                .into(binding.newCatFactImage)
        }
        binding.storeCatFact.setOnClickListener {
            dbCatFactViewModel.saveFact()
            bottomSheet.apply {
                this.state = BottomSheetBehavior.STATE_COLLAPSED
                displayCatFactsList()
            }
        }
        binding.dismissBottomSheet.setOnClickListener {
            bottomSheet.apply {
                this.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    private fun displayCatFactsList() {
        val itemAnimator = DefaultItemAnimator()
        itemAnimator.addDuration = 1000
        binding.catFactsList.itemAnimator = itemAnimator
        binding.emptyViewContainer.alpha = 0.5f
        binding.catFactsList.setEmptyView(binding.emptyViewContainer)
        binding.catFactsList.adapter = catsFactsAdapter
        swipeToDeleteCallback = SwipeToDeleteCallback(catsFactsAdapter, dbCatFactViewModel)
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.catFactsList)
        catFactsViewModel.catFactList.observe(this) { catFacts ->
            catFacts?.let {
                catsFactsAdapter.setFacts(catFacts)
            }
        }
    }

    private fun showErrorMessages() {
        catFactsViewModel.errorMessage.observe(this) {
            Snackbar.make(binding.root, it.toString(), Snackbar.LENGTH_LONG).show()
        }
    }
}