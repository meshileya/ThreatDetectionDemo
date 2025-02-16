package com.techies.threatdetectiondemo.presentation.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techies.threatdetectiondemo.R
import com.techies.threatdetectiondemo.domain.model.Result
import com.techies.threatdetectiondemo.domain.model.ThreatUIItem
import com.techies.threatdetectiondemo.presentation.ui.item.AppProtectionStatus
import com.techies.threatdetectiondemo.presentation.ui.item.ThreatAlertCard
import com.techies.threatdetectiondemo.presentation.ui.item.ThreatItem
import com.techies.threatdetectiondemo.presentation.viewmodel.ThreatsViewModel

@Composable
fun AppSafetyScreen(viewModel: ThreatsViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val threatsState = viewModel.threats.collectAsStateWithLifecycle().value

    LaunchedEffect(threatsState) {
        if (threatsState is Result.Error) {
            val errorMessage = threatsState.message
            Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
    ) {
        TopAppBar(
            title = { Text("Apps safety") },
            backgroundColor = colorResource(id = R.color.background),
            navigationIcon = {
                val context = LocalContext.current
                IconButton(onClick = { (context as? Activity)?.finish() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            elevation = 0.dp
        )

        ThreatAlertCard(viewModel) {
            viewModel.startScan()
        }

        Spacer(modifier = Modifier.height(8.dp))

        AppProtectionStatus(viewModel)

        Spacer(modifier = Modifier.height(8.dp))

        when (threatsState) {
            is Result.Loading -> {
                if (threatsState.message != null)
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is Result.Success -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(18.dp)
                ) {
                    RiskyAppsList(threats = threatsState.data)
                }
//                RiskyAppsList(threats = threatsState.data)
            }


            is Result.Error -> {
            }
        }
    }
}

@Composable
fun RiskyAppsList(threats: List<ThreatUIItem>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(18.dp)
    ) {
        LazyColumn {
            items(items = threats) { threat ->
                ThreatItem(
                    item = threat
                )
            }
        }
    }
}
