package com.techies.threatdetectiondemo.presentation.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techies.threatdetectiondemo.R
import com.techies.threatdetectiondemo.domain.model.ThreatUIItem


@Composable
fun ThreatItem(item: ThreatUIItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(12.dp)
                .background(
                    if (item.isThreat) colorResource(id = R.color.online) else colorResource(
                        id = R.color.offline
                    ), shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = item.threatName, fontWeight = FontWeight.Bold)
            Text(text = item.threatUrlRef, fontSize = 12.sp, color = Color.Gray)
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Right Arrow",
                modifier = Modifier.size(24.dp),
                tint = Color.Gray
            )
        }
    }
}
