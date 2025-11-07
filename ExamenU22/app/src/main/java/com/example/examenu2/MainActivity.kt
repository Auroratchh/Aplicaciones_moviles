package com.example.examenu2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.examenu2.navigation.AppNavigator
import com.example.examenu2.ui.theme.ExamenU2Theme
import com.example.examenu2.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val useDarkMode by settingsViewModel.useDarkMode.collectAsState()

            ExamenU2Theme (darkTheme = useDarkMode) {
                AppNavigator()
            }
        }
    }
}