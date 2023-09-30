package com.danielwaiguru.shoppy.data.sources.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

internal class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val interceptedRequest = chain.request()
        val newRequest = interceptedRequest.newBuilder()
            .header(
                "Accept",
                "application/json"
            )
            .build()
        return chain.proceed(newRequest)
    }
}