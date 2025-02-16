package com.techies.threatdetectiondemo.data.remote.api

import com.techies.threatdetectiondemo.data.model.ThreatResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ThreatApiService {
    @GET("urls/recent/limit/{limit}")
    suspend fun getThreatsAsync(
        @Path("limit") limit: String
    ): Response<ThreatResponse>
}