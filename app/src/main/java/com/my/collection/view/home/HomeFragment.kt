package com.my.collection.view.home

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.my.collection.CollectionsAdapter
import com.my.collection.CollectionsFuncItem
import com.my.collection.R
import com.my.collection.model.api.vo.CollectionItem
import com.my.collection.view.collectiondetail.CollectionDetailFragment
import com.my.collection.view.pagingfooter.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()


    private val collectionsFuncItem by lazy { CollectionsFuncItem(
        onClick = { item ->
            navigateTo(item)
        }
    )
    }

    private val collectionsAdapter by lazy {
        val adapter = CollectionsAdapter(collectionsFuncItem)
        val loadStateListener = { loadStatus: CombinedLoadStates ->
            when (loadStatus.refresh) {
                is LoadState.Error -> {
                    layout_refresh.isRefreshing = false
                }
                is LoadState.Loading -> {
                    layout_refresh.isRefreshing = true
                }
                is LoadState.NotLoading -> {
                    layout_refresh.isRefreshing = false
                }
            }

            when (loadStatus.append) {
                is LoadState.Error -> {
                }
                is LoadState.Loading -> {
                }
                is LoadState.NotLoading -> {
                }
            }
        }
        adapter.addLoadStateListener(loadStateListener)
        adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        setupListener()
    }

    fun setupUI() {
        rv_collections_list.also {
            it.setHasFixedSize(true)
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            it.layoutManager = gridLayoutManager
            val loadStateAdapter = PagingLoadStateAdapter()
            it.adapter = collectionsAdapter.withLoadStateFooter(loadStateAdapter)
            it.itemAnimator = null
        }

        layout_refresh.setOnRefreshListener {
            collectionsAdapter.refresh()
        }

        tv_title.text = "LIST"
        iv_up.visibility = GONE
    }

    fun setupObserver() {
        viewModel.pagingDataResult.observe(viewLifecycleOwner, {
            collectionsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    fun setupListener() {}

    fun navigateTo(item: CollectionItem){
        val bundle = CollectionDetailFragment.createBundle(item)
        findNavController().navigate(R.id.action_fragment_home_to_fragment_collection_detail, bundle)
    }
}