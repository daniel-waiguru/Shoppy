package com.danielwaiguru.shoppy.data.sources.remote

import com.danielwaiguru.shoppy.data.sources.remote.api.ShoppyApiService
import javax.inject.Inject

interface RemoteDataSource {
}

internal class RetrofitDataSource @Inject constructor(
    private val apiService: ShoppyApiService
) : RemoteDataSource {

}