package com.techies.threatdetectiondemo.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.techies.threatdetectiondemo.presentation.ui.screens.AppSafetyScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppSafetyScreen()
        }
    }
}