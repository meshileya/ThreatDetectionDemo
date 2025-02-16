package com.techies.threatdetectiondemo.data.model

import com.techies.threatdetectiondemo.common.capitalise
import com.techies.threatdetectiondemo.domain.model.ThreatUIItem


fun ThreatUrl.toUIItem(): ThreatUIItem {
    return ThreatUIItem(
        threatName = threat.capitalise(),
        isThreat = urlStatus == "online",
        threatUrlRef = urlHausReference ?: ""
    )
}