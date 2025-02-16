package com.techies.threatdetectiondemo.data.repository

import com.techies.threatdetectiondemo.data.local.LocalDataSource
import com.techies.threatdetectiondemo.data.model.ThreatUrl
import com.techies.threatdetectiondemo.data.remote.datasource.RemoteDatasource
import com.techies.threatdetectiondemo.domain.model.Result
import kotlinx.coroutines.flow.Flow

class ThreatRepositoryImpl(
    private val remoteDataSource: RemoteDatasource,
    private val localDataSource: LocalDataSource
) : ThreatRepository {

    override suspend fun getThreats(limit: Int): Flow<Result<List<ThreatUrl>>> {
        return remoteDataSource.getThreats(limit = limit)
    }

    override suspend fun saveAppProtectionStatus(isEnabled: Boolean) {
        localDataSource.saveAppProtectionStatus(isEnabled)
    }

    override fun getAppProtectionStatus(): Flow<Boolean> {
        return localDataSource.getAppProtectionStatus()
    }

    override suspend fun saveLastScanTime(time: String) {
        localDataSource.saveLastScanTime(time)
    }

    override fun getLastScanTime(): Flow<String> {
        return localDataSource.getLastScanTime()
    }

}