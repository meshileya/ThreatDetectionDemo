package com.techies.threatdetectiondemo

import com.techies.threatdetectiondemo.data.local.LocalDataSource
import com.techies.threatdetectiondemo.data.model.ThreatUrl
import com.techies.threatdetectiondemo.data.remote.datasource.RemoteDatasource
import com.techies.threatdetectiondemo.data.repository.ThreatRepositoryImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.techies.threatdetectiondemo.domain.model.Result
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ThreatRepositoryTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var remoteDataSource: RemoteDatasource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    private lateinit var repository: ThreatRepositoryImpl

    @Before
    fun setup() {
        repository = ThreatRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `getThreats should return data from remoteDataSource`() = runTest {
        val threats = listOf(
            ThreatUrl(
                date_added = "",
                host = "",
                id = 1,
                larted = "",
                reporter = "",
                tags = null,
                threat = null,
                url = null,
                urlStatus = null,
                urlHausReference = null
            )
        )
        `when`(remoteDataSource.getThreats(10)).thenReturn(flowOf(Result.Success(threats)))

        val result = repository.getThreats(10).first()

        assertEquals(Result.Success(threats), result)
    }

    @Test
    fun `saveAppProtectionStatus should call localDataSource`() = runTest {
        repository.saveAppProtectionStatus(true)

        verify(localDataSource).saveAppProtectionStatus(true)
    }
}
