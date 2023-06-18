package com.json.catfacts.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.json.catfacts.data.entities.CatFact
import com.json.catfacts.presentation.ui.adapters.CatFactAdapter

class SwipeToDeleteCallback(
    private val adapter: CatFactAdapter,
    private val listener: SwipeToDeleteCallbackListener
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.ACTION_STATE_IDLE,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val catFact = adapter.getItem(position)
        adapter.removeItem(position)
        listener.onItemSwiped(position, catFact)
    }
}

interface SwipeToDeleteCallbackListener {
    fun onItemSwiped(position: Int, catFact: CatFact)
}