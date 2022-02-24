package com.my.collection

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.my.collection.model.api.vo.CollectionItem
import com.my.collection.model.manager.RepositoryManager

class MainViewModel @ViewModelInject constructor(
    private val repositoryManager: RepositoryManager,
) : ViewModel() {

}