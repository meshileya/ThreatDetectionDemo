package com.techies.threatdetectiondemo.domain.model

data class ThreatUIItem(
    val threatName: String,
    val isThreat: Boolean,
    val threatUrlRef: String
)