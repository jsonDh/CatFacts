package com.json.catfacts.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.json.catfacts.R

class EmptyStateRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var emptyView : View? = null

    fun setEmptyView(emptyView: View){
        this.emptyView = emptyView
    }

    private fun checkIfEmpty(){
        emptyView?.let {emptyView ->
            adapter?.let { adapter ->
                val isEmpty = adapter.itemCount == 0
                emptyView.isVisible = isEmpty

                if (isEmpty) {
                    val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                    emptyView.startAnimation(animation)
                } else {
                    emptyView.clearAnimation()
                }
            }
        }
    }

    private val observer : AdapterDataObserver = object : AdapterDataObserver () {
        override fun onChanged() {
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            checkIfEmpty()
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        this.adapter?.unregisterAdapterDataObserver(observer)
        super.setAdapter(adapter)
        this.adapter?.registerAdapterDataObserver(observer)
        checkIfEmpty()
    }

}