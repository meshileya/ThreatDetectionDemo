package com.techies.threatdetectiondemo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.core.app.ApplicationProvider
import com.techies.threatdetectiondemo.common.THREAT_PREF
import com.techies.threatdetectiondemo.data.local.LocalDataSourceImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class LocalDataSourceTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val Context.dataStore by preferencesDataStore(name = THREAT_PREF)

    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var localDataSource: LocalDataSourceImpl

    @Before
    fun setup() {
        hiltRule.inject() // Initialize Hilt dependencies
        val context = ApplicationProvider.getApplicationContext<Context>()
        dataStore = context.dataStore
        localDataSource = LocalDataSourceImpl(context)
    }

    @Test
    fun saveAppProtectionStatus() {
        runTest {
            localDataSource.saveAppProtectionStatus(true)

            val result = localDataSource.getAppProtectionStatus().first()

            assertEquals(true, result)
        }
    }
}
