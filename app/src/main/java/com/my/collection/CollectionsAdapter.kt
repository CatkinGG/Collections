package com.my.collection

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.collection.model.api.vo.CollectionItem
import kotlinx.android.synthetic.main.item_collection.view.*

open class CollectionsAdapter(
    private val collectionsFuncItem: CollectionsFuncItem
): PagingDataAdapter<CollectionItem, CollectionViewHolder>(diffCallback) {

    companion object {
        const val VIEW_TYPE_TODO = 1

        private val diffCallback = object : DiffUtil.ItemCallback<CollectionItem>() {
            override fun areItemsTheSame(
                oldItem: CollectionItem,
                newItem: CollectionItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: CollectionItem,
                newItem: CollectionItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_collection, parent, false),
        )
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        getItem(position)?.run {
            val context = holder.itemView.context
            holder.tvName.text = this.name
            Glide.with(context)
                .load(this.image_url)
                .into(holder.ivCover)
            holder.clRoot.setOnClickListener {
                collectionsFuncItem.onClick.invoke(this)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_TODO
    }

    open fun isDataEmpty(): Boolean {
        return itemCount == 0
    }
}

class CollectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val ivCover: ImageView = itemView.iv_cover
    val tvName: TextView = itemView.tv_name
    val clRoot: ConstraintLayout = itemView.cl_root
}