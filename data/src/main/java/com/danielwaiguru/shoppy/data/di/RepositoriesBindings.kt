package com.danielwaiguru.shoppy.data.di

import com.danielwaiguru.shoppy.data.repositories.ProductsRepositoryImpl
import com.danielwaiguru.shoppy.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesBindings {
    @Binds
    @Singleton
    internal abstract fun bindProductsRepository(
        productsRepositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository
}