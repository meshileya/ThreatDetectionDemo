package com.techies.threatdetectiondemo.data.remote.datasource

import androidx.paging.PagingSource
import com.techies.threatdetectiondemo.data.model.ThreatUrl
import com.techies.threatdetectiondemo.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {
    suspend fun getThreats(limit: Int = 10): Flow<Result<List<ThreatUrl>>>
//    fun getThreats(): PagingSource<Int, ThreatUrl>
}