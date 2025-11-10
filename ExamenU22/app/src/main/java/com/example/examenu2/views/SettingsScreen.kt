package com.example.examenu2.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.examenu2.viewmodels.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val useDarkMode by viewModel.useDarkMode.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración del Tema (DataStore)") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Regresar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(if (useDarkMode) "Tema Oscuro" else "Tema Claro")
                Spacer(modifier = Modifier.width(16.dp))
                Switch(
                    checked = useDarkMode,
                    onCheckedChange = { viewModel.setDarkModePreference(it) }
                )
            }
        }
    }
}