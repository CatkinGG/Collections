package com.my.collection.di

import com.my.collection.model.manager.RepositoryManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object managerModule {

    @Singleton
    @Provides
    fun provideRepositoryManager(
        @ApiModule.RestfulOkHttpClient restfulOkHttpClient: OkHttpClient,
    ): RepositoryManager {
        return RepositoryManager(restfulOkHttpClient)
    }
}
