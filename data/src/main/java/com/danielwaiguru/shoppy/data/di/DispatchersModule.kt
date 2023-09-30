package com.danielwaiguru.shoppy.data.di

import com.danielwaiguru.shoppy.domain.utils.Dispatcher
import com.danielwaiguru.shoppy.domain.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(DispatcherProvider.IO)
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(DispatcherProvider.MAIN)
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Dispatcher(DispatcherProvider.DEFAULT)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Dispatcher(DispatcherProvider.UNCONFINED)
    fun providesUnconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}