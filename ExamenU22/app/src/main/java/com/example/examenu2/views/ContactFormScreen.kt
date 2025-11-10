package com.example.examenu2.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.examenu2.viewmodels.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactFormScreen(navController: NavController) {
    val viewModel: ContactViewModel = hiltViewModel()

    var name by rememberSaveable { mutableStateOf("") }
    var subject by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    val contactList by viewModel.allContacts.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Formulario de Contacto (Room)") },
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
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre Completo") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = subject,
                onValueChange = { subject = it },
                label = { Text("Asunto") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                viewModel.addContact(name, subject, email)
                name = ""
                subject = ""
                email = ""
            }) {
                Text("Guardar Contacto")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Contactos Guardados:", style = MaterialTheme.typography.titleMedium)

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(contactList) { item ->
                    Text(
                        text = "ID: ${item.id} | ${item.name} | ${item.subject} | ${item.email}",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}