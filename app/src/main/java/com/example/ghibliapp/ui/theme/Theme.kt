package com.example.ghibliapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Blue80,
    secondary = BlueGrey80,
    tertiary = Green1,
    primaryContainer = Blue80.copy(alpha = 0.3f)
)

private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    secondary = BlueGrey40,
    tertiary = Green2,
    primaryContainer = LightBlue40.copy(alpha = 0.2f)
)

@Composable
fun GhibliAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}