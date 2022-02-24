package com.my.collection.model.repository

import com.my.collection.model.api.ApiService

class CollectionsRepository constructor(private val apiService: ApiService) {
    suspend fun getCollections(owner: String, offset: Int, limit: Int)
                    = apiService.getCollections(owner, offset, limit)

        suspend fun getCollection(contract_address: String, token_id: String)
            = apiService.getCollection(contract_address, token_id)
}