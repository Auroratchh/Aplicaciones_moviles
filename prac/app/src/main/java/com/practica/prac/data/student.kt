package com.practica.prac.data


data class Student(
    val id: Long = System.currentTimeMillis(),
    val nombre: String,
    val apellidos: String,
    val grado: String,
    val grupo: String,
    val promedio: Double
)

data class GroupAverage(
    val grado: String,
    val grupo: String,
    val promedio: Double,
    val count: Int
)