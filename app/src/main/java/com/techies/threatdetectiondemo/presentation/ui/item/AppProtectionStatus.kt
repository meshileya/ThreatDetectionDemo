package com.techies.threatdetectiondemo.presentation.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techies.threatdetectiondemo.R
import com.techies.threatdetectiondemo.presentation.viewmodel.ThreatsViewModel

@Composable
fun AppProtectionStatus(viewModel: ThreatsViewModel) {

    val appProtectionEnabled = viewModel.appProtectionStatus.collectAsStateWithLifecycle().value

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.text_background),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "App protection", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = if (appProtectionEnabled) "Enabled" else "Disabled",
                color = if (appProtectionEnabled) Color.Blue else Color.Red,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}