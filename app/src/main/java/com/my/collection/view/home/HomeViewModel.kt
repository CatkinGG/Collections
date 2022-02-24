package com.my.collection.view.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.my.collection.CollectionsPagingSource
import com.my.collection.model.api.vo.CollectionItem
import com.my.collection.model.manager.RepositoryManager

class HomeViewModel @ViewModelInject constructor(
    private val repositoryManager: RepositoryManager
) : ViewModel() {
    private val _pagingDataResult = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { CollectionsPagingSource(repositoryManager) }
    ).flow
        .cachedIn(viewModelScope).asLiveData()
        .let { it as MutableLiveData<PagingData<CollectionItem>> }
    val pagingDataResult: LiveData<PagingData<CollectionItem>> = _pagingDataResult
}