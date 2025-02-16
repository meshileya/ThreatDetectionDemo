package com.techies.threatdetectiondemo.data.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveAppProtectionStatus(enabled: Boolean)
    suspend fun saveLastScanTime(time: String)

    fun getLastScanTime(): Flow<String>
    fun getAppProtectionStatus(): Flow<Boolean>
}