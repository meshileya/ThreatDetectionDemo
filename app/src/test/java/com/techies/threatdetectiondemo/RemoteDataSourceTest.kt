package com.techies.threatdetectiondemo

import com.techies.threatdetectiondemo.data.model.ThreatResponse
import com.techies.threatdetectiondemo.data.model.ThreatUrl
import com.techies.threatdetectiondemo.data.remote.api.ThreatApiService
import com.techies.threatdetectiondemo.data.remote.datasource.RemoteDataSourceImpl
import com.techies.threatdetectiondemo.domain.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var service: ThreatApiService

    private lateinit var remoteDataSource: RemoteDataSourceImpl

    @Before
    fun setup() {
        remoteDataSource = RemoteDataSourceImpl(service)
    }

    @Test
    fun `getThreats should return success when API call is successful`() = runTest {
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
        val response = Response.success(ThreatResponse("", threats))
        `when`(service.getThreatsAsync("10")).thenReturn(response)

        val result = remoteDataSource.getThreats(10).first()

        assert(result is Result.Success)
    }

    @Test
    fun `getThreats should return error on HttpException`() = runTest {
        `when`(service.getThreatsAsync("10")).thenThrow(
            HttpException(
                Response.error<Any>(
                    500,
                    "".toResponseBody()
                )
            )
        )

        val result = remoteDataSource.getThreats(10).first()

        assert(result is Result.Error)
    }
}
