package com.my.collection.view.collectiondetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.my.collection.model.api.ApiResult
import com.my.collection.model.api.vo.CollectionItem
import com.my.collection.model.manager.RepositoryManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CollectionDetailViewModel @ViewModelInject constructor(
    private val repositoryManager: RepositoryManager
) : ViewModel() {
    private val _getCollectionResult = MutableLiveData<ApiResult<CollectionItem>>()
    val getCollectionResult: LiveData<ApiResult<CollectionItem>> = _getCollectionResult

    fun getCollection(contract_address: String, token_id: String) {
        viewModelScope.launch {
            flow {
                val resp = repositoryManager.collectionsRepository.getCollection(contract_address, token_id)
                if (!resp.isSuccessful) throw HttpException(resp)
                emit(ApiResult.success(resp.body()))
            }
                .flowOn(Dispatchers.IO)
                .catch { e -> emit(ApiResult.error(e)) }
                .collect { _getCollectionResult.postValue(it) }
        }
    }
}