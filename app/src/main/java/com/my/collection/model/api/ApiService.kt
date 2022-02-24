package com.my.collection.model.api

import com.my.collection.model.api.vo.CollectionItem
import com.my.collection.model.api.vo.CollectionsItem
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/assets")
    suspend fun getCollections(
        @Query("owner") owner: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<CollectionsItem>

    @GET("/asset/{contract_address}/{token_id}")
    suspend fun getCollection(
        @Path("contract_address") contract_address: String,
        @Path("token_id") token_id: String
    ): Response<CollectionItem>
}