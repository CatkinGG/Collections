package com.my.collection.view.collectiondetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.my.collection.R
import com.my.collection.model.api.ApiResult
import com.my.collection.model.api.vo.CollectionItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_collection_detail.*
import timber.log.Timber
import android.content.Intent
import android.net.Uri
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.toolbar.*


@AndroidEntryPoint
class CollectionDetailFragment : Fragment(R.layout.fragment_collection_detail) {
    companion object {
        const val KEY_DATA = "data"

        fun createBundle(
            collectionItem: CollectionItem
        ): Bundle {
            return Bundle().also {
                it.putSerializable(KEY_DATA, collectionItem)
            }
        }
    }
    private val viewModel: CollectionDetailViewModel by viewModels()

    private var collectionItem: CollectionItem? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getSerializable(KEY_DATA)?.let { data ->
            data as CollectionItem
            collectionItem = data
        }
        setupUI()
        setupObserver()
        setupListener()
        viewModel.getCollection(
            collectionItem?.asset_contract?.address?:"",
            collectionItem?.token_id?:""
        )
    }

    fun setupUI() {
    }

    fun setupObserver() {
        viewModel.getCollectionResult.observe(viewLifecycleOwner, {
            when (it) {
                is ApiResult.Success -> {
                    it.result?.run{
                        Glide.with(requireContext())
                            .load(this.image_url)
                            .into(iv_image)
                        tv_title.text = this.name
                        tv_name.text = this.name
                        tv_description.text = this.description
                        bt_link.setOnClickListener {
                            val i = Intent(Intent.ACTION_VIEW)
                            i.data = Uri.parse(this.permalink?:"")
                            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(i)
                        }
                    }
                }
                is ApiResult.Error -> {
                    Timber.d("onError: ${it.throwable.message}")
                }
            }
        })
    }

    fun setupListener() {
        iv_up.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}