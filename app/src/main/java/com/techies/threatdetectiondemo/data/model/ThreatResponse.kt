package com.techies.threatdetectiondemo.data.model

import com.google.gson.annotations.SerializedName

data class ThreatResponse(
    @SerializedName("query_status")
    val queryStatus: String?,
    val urls: List<ThreatUrl>?
)

data class ThreatUrl(
    val blacklists: ThreatBlacklists,
    val date_added: String,
    val host: String,
    val id: Int,
    val larted: String,
    val reporter: String,
    val tags: List<String>,
    val threat: String?,
    val url: String,
    @SerializedName("url_status")
    val urlStatus: String?,
    @SerializedName("urlhaus_reference")
    val urlHausReference: String?
)

data class ThreatBlacklists(
    val spamhaus_dbl: String,
    val surbl: String
)