package com.my.collection

import androidx.paging.PagingSource
import com.my.collection.model.api.vo.CollectionItem
import com.my.collection.model.api.vo.CollectionsItem
import com.my.collection.model.manager.RepositoryManager
import retrofit2.HttpException
import timber.log.Timber

class CollectionsPagingSource(
    private val repositoryManager: RepositoryManager
) : PagingSource<Int, CollectionItem>() {
    companion object {
        const val PER_LIMIT = 20
        const val OWNER = "0x19818f44faf5a217f619aff0fd487cb2a55cca65"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionItem> {
        return try {
            val page = params.key ?: 0

            val result = repositoryManager.collectionsRepository.getCollections(OWNER, page, PER_LIMIT)
            if (!result.isSuccessful) throw HttpException(result)

            val data = result.body()

            val nextKey = when {
                data?.assets?.size ?: 0 >= PER_LIMIT -> page + 1
                else -> null
            }

            return LoadResult.Page(
                data = data?.assets ?: arrayListOf(),
                prevKey = null,
                nextKey = nextKey
            )

        } catch (exception: Exception) {
            Timber.e(exception)
            LoadResult.Error(exception)
        }
    }
}