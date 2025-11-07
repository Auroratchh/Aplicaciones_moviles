package com.example.model.viewModels

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel


class CounterViewModel : ViewModel() {
    private val _count = mutableIntStateOf(0)

    val count = _count

    fun add() {
        _count.value++
    }
}