package com.techies.threatdetectiondemo.data.repository

import com.techies.threatdetectiondemo.data.model.ThreatUrl
import kotlinx.coroutines.flow.Flow
import com.techies.threatdetectiondemo.domain.model.Result

interface ThreatRepository {
    suspend fun getThreats(limit: Int): Flow<Result<List<ThreatUrl>>>

    suspend fun saveAppProtectionStatus(isEnabled: Boolean)
    fun getAppProtectionStatus(): Flow<Boolean>

    suspend fun saveLastScanTime(time: String)
    fun getLastScanTime(): Flow<String>
}