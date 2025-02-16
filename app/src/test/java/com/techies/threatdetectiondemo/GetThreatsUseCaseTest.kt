package com.techies.threatdetectiondemo

import com.techies.threatdetectiondemo.data.model.ThreatUrl
import com.techies.threatdetectiondemo.data.model.toUIItem
import com.techies.threatdetectiondemo.data.repository.ThreatRepository
import com.techies.threatdetectiondemo.domain.usecase.GetThreatsUseCaseImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import com.techies.threatdetectiondemo.domain.model.Result

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetThreatsUseCaseTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var useCase: GetThreatsUseCaseImpl

    @Mock
    private lateinit var repository: ThreatRepository

    @Before
    fun setup() {
        useCase = GetThreatsUseCaseImpl(repository)
    }

    @Test
    fun `when getThreats is called, should return transformed UI items`() = runTest {
        val threatList = listOf(
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
        val expectedResult = Result.Success(threatList.map { it.toUIItem() })

        `when`(repository.getThreats(10)).thenReturn(flowOf(Result.Success(threatList)))

        val result = useCase.invoke(10).first()

        assertEquals(expectedResult, result)
    }

    @Test
    fun `enableAppProtection should update protection status and last scan time`() = runTest {
        useCase.enableAppProtection()

        verify(repository).saveAppProtectionStatus(true)
        verify(repository).saveLastScanTime(anyString())
    }
}
