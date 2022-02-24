package com.my.collection.view.pagingfooter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.my.collection.R
import kotlinx.android.synthetic.main.item_loading_state.view.*

class PagingLoadStateViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_loading_state, parent, false)
) {
    private val progressBar = itemView.progress_bar

    fun bindTo(loadState: LoadState) {
        progressBar.visibility = takeIf { loadState is LoadState.Loading }
            ?.let { View.VISIBLE }
            ?: let { View.GONE }
    }
}