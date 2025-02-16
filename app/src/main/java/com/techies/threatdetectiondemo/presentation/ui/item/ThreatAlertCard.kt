package com.techies.threatdetectiondemo.presentation.ui.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techies.threatdetectiondemo.R
import com.techies.threatdetectiondemo.presentation.viewmodel.ThreatsViewModel

@Composable
fun ThreatAlertCard(viewModel: ThreatsViewModel, onStartScan: () -> Unit) {
    val lastScanTime = viewModel.lastScanTime.collectAsStateWithLifecycle().value

    Card(
        backgroundColor = colorResource(id = R.color.secondary),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_alert),
                contentDescription = "Warning",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(50.dp)
                    .offset(x = (-4).dp, y = 2.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Device at high risk!",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Last scan - $lastScanTime",
                color = Color.White,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { onStartScan() },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.asset)),
                modifier = Modifier
                    .align(Alignment.Start),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Start Scan",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}