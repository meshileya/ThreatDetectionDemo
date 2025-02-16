package com.techies.threatdetectiondemo.data.remote.datasource

import com.techies.threatdetectiondemo.data.model.ThreatUrl
import com.techies.threatdetectiondemo.data.remote.api.ThreatApiService
import com.techies.threatdetectiondemo.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RemoteDataSourceImpl(private val service: ThreatApiService) : RemoteDatasource {
    override suspend fun getThreats(limit: Int): Flow<Result<List<ThreatUrl>>> = flow {
        try {
            emit(Result.Loading("Fetching threats..."))

            val response = service.getThreatsAsync(limit = "$limit")

            if (response.isSuccessful) {
                val data = response.body()?.urls.orEmpty()
                emit(Result.Success(data))
            } else {
                emit(Result.Error(response.code(), response.message()))
            }
        } catch (ex: Exception) {
            when (ex) {
                is HttpException -> emit(
                    Result.Error(ex.code(), ex.localizedMessage ?: "Something went wrong")
                )

                else -> emit(
                    Result.Error(-1, ex.message ?: "Unknown error")
                )
            }
        }
    }
}