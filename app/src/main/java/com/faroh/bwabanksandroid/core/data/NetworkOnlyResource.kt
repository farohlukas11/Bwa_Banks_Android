package com.faroh.bwabanksandroid.core.data

import com.faroh.bwabanksandroid.core.data.source.remote.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkOnlyResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emitAll(loadFromNetwork(apiResponse.data).map {
                    Resource.Success(it)
                })
            }

            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }

            else -> {
                emit(Resource.Error("Data is Empty!"))
            }
        }
    }


    protected abstract fun loadFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}