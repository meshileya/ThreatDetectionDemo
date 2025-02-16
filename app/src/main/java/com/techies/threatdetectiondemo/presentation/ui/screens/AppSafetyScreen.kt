package com.techies.threatdetectiondemo.presentation.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
            Toast.makeText(context, "Error: ${threatsState.message}", Toast.LENGTH_LONG).show()
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Apps safety") },
            backgroundColor = colorResource(id = R.color.background),
            navigationIcon = {
                IconButton(onClick = { (context as? Activity)?.finish() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            elevation = 0.dp
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.background))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ThreatAlertCard(viewModel) { viewModel.startScan() }
                AppProtectionStatus(viewModel)

                when (threatsState) {
                    is Result.Loading -> {
                        if (threatsState.message != null)
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                    }

                    is Result.Success -> RiskyAppsList(threatsState.data)

                    is Result.Error -> Text(
                        text = "Error: ${threatsState.message}",
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun RiskyAppsList(threats: List<ThreatUIItem>) {
    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.background))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            threats.forEach { threat ->
                ThreatItem(item = threat)
            }
        }
    }
}