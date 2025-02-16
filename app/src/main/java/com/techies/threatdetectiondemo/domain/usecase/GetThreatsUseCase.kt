package com.techies.threatdetectiondemo.domain.usecase

import com.techies.threatdetectiondemo.domain.model.Result
import com.techies.threatdetectiondemo.domain.model.ThreatUIItem
import kotlinx.coroutines.flow.Flow

interface GetThreatsUseCase {
    suspend fun invoke(limit: Int): Flow<Result<List<ThreatUIItem>>>
    suspend fun enableAppProtection()
    fun getLastScanTime(): Flow<String>
    fun getAppProtectionStatus(): Flow<Boolean>
}
