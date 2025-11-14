package com.practica.prac.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.practica.prac.data.GroupAverage
import com.practica.prac.data.Student

class StudentViewModel : ViewModel() {
    private val _students = mutableStateListOf(
        Student(1, "Juan", "Pérez García", "10°", "A", 8.5),
        Student(2, "María", "Rodríguez Silva", "10°", "B", 9.2),
        Student(3, "Carlos", "Martínez Ruiz", "11°", "A", 7.8)
    )
    val students: List<Student> = _students

    fun addStudent(student: Student) {
        _students.add(student)
    }

    fun deleteStudent(id: Long) {
        _students.removeIf { it.id == id }
    }

    fun updateStudent(id: Long, updatedStudent: Student) {
        val index = _students.indexOfFirst { it.id == id }
        if (index != -1) {
            _students[index] = updatedStudent.copy(id = id)
        }
    }

    fun getTopStudents(): List<Student> {
        return _students.sortedByDescending { it.promedio }.take(3)
    }

    fun getAverageByGroup(): List<GroupAverage> {
        return _students.groupBy { "${it.grado}-${it.grupo}" }
            .map { (key, students) ->
                val parts = key.split("-")
                GroupAverage(
                    grado = parts[0],
                    grupo = parts[1],
                    promedio = students.map { it.promedio }.average(),
                    count = students.size
                )
            }
    }
}