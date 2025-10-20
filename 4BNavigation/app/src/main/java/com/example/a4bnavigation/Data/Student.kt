package com.example.a4bnavigation.Data

data class Student(
    val id: Long,
    val name: String,
    val description: String
)

val students = listOf<Student>(
    Student(1L, "Emilia", "Es la jefa de grupo"),
    Student(2L, "Nadia", "Es la amiga de Emilia"),
    Student(3L, "Luis", "Es el amigo de  la amiga de Emilia"),
    Student(4L, "Antonio", "Es el amigo del amigo  de  la amiga de Emilia"),
    Student(5L, "Angel", "Es el amigo del amigo del amigo  de  la amiga de Emilia"),
    Student(6L, "Emma", "Descripción"),
)