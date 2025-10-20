package com.example.a4bnavigation.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun Description(navController: NavController, description: String){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Description")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ){
        DescriptionInfo(it, description)
    }


}

@Composable
fun DescriptionInfo(paddingValues: PaddingValues, description: String){
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        Text("${description}")
    }
}


