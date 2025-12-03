package com.practica.practicaintents

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreen() {
    val context = LocalContext.current
    var fotoUri by remember { mutableStateOf<Uri?>(null) }
    var mostrar by remember { mutableStateOf(false) }

    val permiso = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { }

    val camara = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        mostrar = success
    }

    LaunchedEffect(Unit) {
        permiso.launch(Manifest.permission.CAMERA)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Cámara práctica") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    val archivo = File.createTempFile("foto_", ".jpg", context.cacheDir)
                    val uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.provider",
                        archivo
                    )
                    fotoUri = uri
                    mostrar = false
                    camara.launch(uri)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Abrir cámara")
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (mostrar && fotoUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(fotoUri),
                    contentDescription = "Foto",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentScale = ContentScale.Fit
                )
            } else {
                Text("Presiona el botón plisss")
            }
        }
    }
}