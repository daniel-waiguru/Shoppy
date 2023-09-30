package com.danielwaiguru.shoppy.data.di

import com.danielwaiguru.shoppy.data.sources.remote.RemoteDataSource
import com.danielwaiguru.shoppy.data.sources.remote.RetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Singleton
    @Binds
    internal abstract fun bindRemoteDataSource(
        retrofitDataSource: RetrofitDataSource
    ): RemoteDataSource
}