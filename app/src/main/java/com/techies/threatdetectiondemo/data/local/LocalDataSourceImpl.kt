package com.techies.threatdetectiondemo.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.techies.threatdetectiondemo.common.LAST_SCAN_TIME
import com.techies.threatdetectiondemo.common.PROTECTION_STATUS
import com.techies.threatdetectiondemo.common.THREAT_PREF
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = THREAT_PREF)


class LocalDataSourceImpl(private val context: Context) : LocalDataSource {

    companion object {
        private val APP_PROTECTION_KEY = booleanPreferencesKey(PROTECTION_STATUS)
        private val LAST_SCAN_KEY = stringPreferencesKey(LAST_SCAN_TIME)
    }

    override suspend fun saveAppProtectionStatus(isEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[APP_PROTECTION_KEY] = isEnabled
        }
    }

    override suspend fun saveLastScanTime(time: String) {
        context.dataStore.edit { preferences ->
            preferences[LAST_SCAN_KEY] = time
        }
    }

    override fun getLastScanTime(): Flow<String> {
        return context.dataStore.data
            .map { preferences -> preferences[LAST_SCAN_KEY] ?: "Never" }
    }

    override fun getAppProtectionStatus(): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences -> preferences[APP_PROTECTION_KEY] == true }
    }
}