package com.techies.threatdetectiondemo.domain.usecase

import com.techies.threatdetectiondemo.data.model.toUIItem
import com.techies.threatdetectiondemo.data.repository.ThreatRepository
import com.techies.threatdetectiondemo.domain.model.Result
import com.techies.threatdetectiondemo.domain.model.ThreatUIItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GetThreatsUseCaseImpl(
    private val repository: ThreatRepository
) : GetThreatsUseCase {

    override suspend fun invoke(limit: Int): Flow<Result<List<ThreatUIItem>>> {
        return repository.getThreats(limit = limit).map { result ->
            when (result) {
                is Result.Error -> {
                    Result.Error(code = result.code, message = result.message)
                }

                is Result.Loading -> {
                    Result.Loading(result.message)
                }

                is Result.Success -> {
                    val uiItems = result.data.map { threat ->
                        threat.toUIItem()
                    }
                    Result.Success(uiItems)
                }
            }
        }
    }

    override fun getAppProtectionStatus(): Flow<Boolean> = repository.getAppProtectionStatus()
    override fun getLastScanTime(): Flow<String> = repository.getLastScanTime()

    override suspend fun enableAppProtection() {
        repository.saveAppProtectionStatus(true)
        val currentTime = SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault()).format(Date())
        repository.saveLastScanTime(currentTime)
    }
}