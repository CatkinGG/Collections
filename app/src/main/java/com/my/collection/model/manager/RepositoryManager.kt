package com.my.collection.model.manager

import com.google.gson.Gson
import com.my.collection.model.api.ApiService
import com.my.collection.model.repository.CollectionsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RepositoryManager(
    private val restfulOkHttpClient: OkHttpClient
) {

    companion object{
        const val MEDIA_TYPE_JSON = "application/json"
    }
    val domain = "https://api.opensea.io/api/v1/ "

    val collectionsRepository by lazy { CollectionsRepository(apiService) }

    private val apiService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        restfulOkHttpClient.interceptors.toMutableList().add(interceptor)

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(restfulOkHttpClient)
            .baseUrl(domain)
            .build()
            .create(ApiService::class.java)
    }
}