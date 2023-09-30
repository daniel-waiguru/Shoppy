package com.danielwaiguru.shoppy.data.sources.remote.utils

import com.danielwaiguru.shoppy.domain.utils.ErrorResponse
import com.danielwaiguru.shoppy.domain.utils.ResultWrapper
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.reflect.KClass

private val networkExceptions: List<KClass<out IOException>> =
    listOf(
        SocketTimeoutException::class,
        ConnectException::class,
        UnknownHostException::class
    )
fun parseErrorBody(throwable: HttpException): ErrorResponse? = try {
    throwable.response()?.errorBody()?.toString()?.let {
        val moshi = Moshi.Builder()
            .build()
        val jsonAdapter = moshi.adapter(ErrorResponse::class.java)
        jsonAdapter.fromJson(it)
    }
} catch (e: Exception) {
    e.printStackTrace()
    null
}
fun <T> flowSafeCall(
    dispatcher: CoroutineDispatcher,
    block: suspend () -> T
): Flow<ResultWrapper<T>> = flow {
    emit(ResultWrapper.Loading)
    emit(ResultWrapper.Success(block.invoke()))
}.catch { throwable ->
    if (throwable is CancellationException) {
        throw throwable
    }
    when (throwable) {
        is HttpException -> {
            val errorResponse = parseErrorBody(throwable)
            emit(
                ResultWrapper.Error(
                    errorMessage = errorResponse?.message
                )
            )
        }

        else -> {
            if (throwable::class in networkExceptions) {
                emit(
                    ResultWrapper.Error(
                        errorMessage = "Check your connection and try again"
                    )
                )
            } else {
                emit(
                    ResultWrapper.Error(
                        errorMessage = throwable.message ?: "An error occurred. Try again"
                    )
                )
            }
        }
    }
}.flowOn(dispatcher)

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            if (throwable is CancellationException) {
                throw throwable
            }
            when (throwable) {
                is HttpException -> {
                    val errorResponse = parseErrorBody(throwable)
                    ResultWrapper.Error(errorResponse?.message)
                }

                else -> {
                    if (throwable::class in networkExceptions) {
                        ResultWrapper.Error(
                            errorMessage = "Check your connection and try again"
                        )
                    } else {
                        ResultWrapper.Error(
                            errorMessage = throwable.message ?: "An error occurred. Try again"
                        )
                    }
                }
            }
        }
    }
}
