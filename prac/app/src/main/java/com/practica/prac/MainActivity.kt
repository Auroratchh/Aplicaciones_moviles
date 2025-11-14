package com.practica.prac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practica.prac.data.Student
import com.practica.prac.screens.DashboardScreen
import com.practica.prac.screens.StatisticsScreen
import com.practica.prac.screens.StudentsListScreen
import com.practica.prac.viewModel.StudentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                StudentManagementApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentManagementApp(viewModel: StudentViewModel = viewModel()) {
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf("students_list") }
    var editingStudent by remember { mutableStateOf<Student?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Sistema de Gestión Estudiantil",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        currentRoute = "students_list"
                        navController.navigate("students_list") {
                            popUpTo("students_list") { inclusive = true }
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = if (currentRoute == "students_list") {
                        ButtonDefaults.buttonColors()
                    } else {
                        ButtonDefaults.outlinedButtonColors()
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Lista")
                }

                Button(
                    onClick = {
                        currentRoute = "dashboard"
                        editingStudent = null
                        navController.navigate("dashboard") {
                            popUpTo("students_list")
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = if (currentRoute == "dashboard") {
                        ButtonDefaults.buttonColors()
                    } else {
                        ButtonDefaults.outlinedButtonColors()
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Agregar")
                }

                Button(
                    onClick = {
                        currentRoute = "statistics"
                        navController.navigate("statistics") {
                            popUpTo("students_list")
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = if (currentRoute == "statistics") {
                        ButtonDefaults.buttonColors()
                    } else {
                        ButtonDefaults.outlinedButtonColors()
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Estadísticas")
                }
            }

            NavHost(
                navController = navController,
                startDestination = "students_list",
                modifier = Modifier.fillMaxSize()
            ) {
                composable("students_list") {
                    LaunchedEffect(Unit) { currentRoute = "students_list" }
                    StudentsListScreen(
                        students = viewModel.students,
                        onEdit = { student ->
                            editingStudent = student
                            currentRoute = "dashboard"
                            navController.navigate("dashboard")
                        },
                        onDelete = { id -> viewModel.deleteStudent(id) }
                    )
                }
                composable("dashboard") {
                    LaunchedEffect(Unit) { currentRoute = "dashboard" }
                    DashboardScreen(
                        editingStudent = editingStudent,
                        onAddStudent = { student ->
                            viewModel.addStudent(student)
                            editingStudent = null
                            currentRoute = "students_list"
                            navController.navigate("students_list") {
                                popUpTo("students_list") { inclusive = true }
                            }
                        },
                        onUpdateStudent = { id, student ->
                            viewModel.updateStudent(id, student)
                            editingStudent = null
                            currentRoute = "students_list"
                            navController.navigate("students_list") {
                                popUpTo("students_list") { inclusive = true }
                            }
                        },
                        onCancel = {
                            editingStudent = null
                            currentRoute = "students_list"
                            navController.navigate("students_list") {
                                popUpTo("students_list") { inclusive = true }
                            }
                        }
                    )
                }
                composable("statistics") {
                    LaunchedEffect(Unit) { currentRoute = "statistics" }
                    StatisticsScreen(viewModel = viewModel)
                }
            }
        }
    }
}