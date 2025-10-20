package com.example.appmvil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appmvil.ui.theme.AppMóvilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMóvilTheme {
                Scaffold {
                    Content(it)
                }
            }
        }
    }
}

@Composable
fun Content(innerPaddingValues: PaddingValues){
    column (
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxSize()
            .background(color = Color.blue)
    ){
        Button(
            onClick ={
                println("Hola mundo")
            }
        )
    }
}