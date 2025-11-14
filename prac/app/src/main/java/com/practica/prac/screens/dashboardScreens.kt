package com.practica.prac.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.practica.prac.data.Student


@Composable
fun DashboardScreen(
    editingStudent: Student?,
    onAddStudent: (Student) -> Unit,
    onUpdateStudent: (Long, Student) -> Unit,
    onCancel: () -> Unit
) {
    var nombre by remember(editingStudent) { mutableStateOf(editingStudent?.nombre ?: "") }
    var apellidos by remember(editingStudent) { mutableStateOf(editingStudent?.apellidos ?: "") }
    var grado by remember(editingStudent) { mutableStateOf(editingStudent?.grado ?: "") }
    var grupo by remember(editingStudent) { mutableStateOf(editingStudent?.grupo ?: "") }
    var promedio by remember(editingStudent) { mutableStateOf(editingStudent?.promedio?.toString() ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            if (editingStudent != null) "Editar Estudiante" else "Agregar Estudiante",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = apellidos,
                    onValueChange = { apellidos = it },
                    label = { Text("Apellidos") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = grado,
                        onValueChange = { grado = it },
                        label = { Text("Grado") },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        placeholder = { Text("Ej: 10°") }
                    )

                    OutlinedTextField(
                        value = grupo,
                        onValueChange = { grupo = it },
                        label = { Text("Grupo") },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        placeholder = { Text("Ej: A") }
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = promedio,
                    onValueChange = { promedio = it },
                    label = { Text("Promedio") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = { Text("Ej: 8.5") }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            if (nombre.isNotBlank() && apellidos.isNotBlank() &&
                                grado.isNotBlank() && grupo.isNotBlank() && promedio.isNotBlank()) {
                                val student = Student(
                                    nombre = nombre,
                                    apellidos = apellidos,
                                    grado = grado,
                                    grupo = grupo,
                                    promedio = promedio.toDoubleOrNull() ?: 0.0
                                )
                                if (editingStudent != null) {
                                    onUpdateStudent(editingStudent.id, student)
                                } else {
                                    onAddStudent(student)
                                }
                                nombre = ""
                                apellidos = ""
                                grado = ""
                                grupo = ""
                                promedio = ""
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (editingStudent != null) "Actualizar" else "Agregar")
                    }

                    if (editingStudent != null) {
                        OutlinedButton(
                            onClick = {
                                onCancel()
                                nombre = ""
                                apellidos = ""
                                grado = ""
                                grupo = ""
                                promedio = ""
                            },
                            modifier = Modifier.weight(0.5f)
                        ) {
                            Text("Cancelar")
                        }
                    }
                }
            }
        }
    }
}