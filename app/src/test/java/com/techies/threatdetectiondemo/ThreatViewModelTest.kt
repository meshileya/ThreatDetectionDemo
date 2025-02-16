package com.techies.threatdetectiondemo

import com.techies.threatdetectiondemo.domain.model.Result
import com.techies.threatdetectiondemo.domain.model.ThreatUIItem
import com.techies.threatdetectiondemo.domain.usecase.GetThreatsUseCase
import com.techies.threatdetectiondemo.presentation.viewmodel.ThreatsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
//@HiltAndroidTest
class ThreatsViewModelTest {

    private lateinit var useCase: GetThreatsUseCase
    private lateinit var viewModel: ThreatsViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()

        useCase = mock()
        viewModel = ThreatsViewModel(useCase)

        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `startScan should update _threats with Result Success`() = runTest {
        // Given
        val mockThreats =
            listOf(ThreatUIItem("Malware Download", false, "https://urlhaus.abuse.ch/url/3441421/"))
        val result = Result.Success(mockThreats)
        whenever(useCase.invoke(10)).thenReturn(flowOf(result))

        // When
        viewModel.startScan(10)

        // Then
        assertEquals(Result.Success(mockThreats), viewModel.threats.value)
    }

    @Test
    fun `startScan should handle Result Error`() = runTest {
        // Given
        val errorMessage = "Network Error"
        val result = Result.Error(code = -1, message = errorMessage)
        whenever(useCase.invoke(10)).thenReturn(flowOf(result))

        // When
        viewModel.startScan(10)

        // Then
        assertEquals(Result.Error(-1, errorMessage), viewModel.threats.value)
    }

    @Test
    fun `enableAppProtection should call useCase enableAppProtection`() = runTest {
        // Given
        viewModel = ThreatsViewModel(useCase)

        coEvery { useCase.enableAppProtection() } just Runs

        // When
        viewModel.startScan(10)

        // Then
        coVerify(exactly = 1) { useCase.enableAppProtection() }
    }

    @Test
    fun `appProtectionStatus should be false initially`() = runTest {
        // Given
        val appProtectionFlow = flowOf(false) // Mock flow
        whenever(useCase.getAppProtectionStatus()).thenReturn(appProtectionFlow)

        // When
        val appProtectionStatus = viewModel.appProtectionStatus.first()

        // Then
        assertEquals(false, appProtectionStatus)
    }

    @Test
    fun `lastScanTime should return 'Never' initially`() = runTest {
        // Given
        val useCaseMock = mock<GetThreatsUseCase>()
        val lastScanTimeFlow = flowOf("Never")
        whenever(useCaseMock.getLastScanTime()).thenReturn(lastScanTimeFlow)

        // When
        val lastScanTime = viewModel.lastScanTime.first()

        // Then
        assertEquals("Never", lastScanTime)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
