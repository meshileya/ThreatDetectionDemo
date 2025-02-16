package com.techies.threatdetectiondemo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techies.threatdetectiondemo.domain.model.ThreatUIItem
import com.techies.threatdetectiondemo.domain.usecase.GetThreatsUseCase
import com.techies.threatdetectiondemo.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThreatsViewModel @Inject constructor(private val useCase: GetThreatsUseCase) : ViewModel() {

    private val _threats = MutableStateFlow<Result<List<ThreatUIItem>>>(Result.Loading(null))
    val threats: StateFlow<Result<List<ThreatUIItem>>> get() = _threats

    val appProtectionStatus: StateFlow<Boolean> = useCase.getAppProtectionStatus()
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    val lastScanTime: StateFlow<String> = useCase.getLastScanTime()
        .stateIn(viewModelScope, SharingStarted.Lazily, "Never")

    fun startScan(limit: Int = 10) {
        viewModelScope.launch {
            try {
                useCase.invoke(limit).collect { result ->
                    _threats.value = result
                }
            } catch (e: Exception) {
                _threats.value = Result.Error(-99, "Error occurred during scan: ${e.message}")
            }
            try {
                useCase.enableAppProtection()
            } catch (e: Exception) {
                _threats.value =
                    Result.Error(-99, "Error occurred during enabling protection: ${e.message}")
            }
        }
    }


}
