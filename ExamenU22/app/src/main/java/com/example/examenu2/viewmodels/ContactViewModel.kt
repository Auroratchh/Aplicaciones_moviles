package com.example.examenu2.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenu2.data.ContactDao
import com.example.examenu2.data.ContactEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactDao: ContactDao
) : ViewModel() {

    val allContacts: StateFlow<List<ContactEntry>> = contactDao.fetchAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addContact(name: String, subject: String, email: String) {
        viewModelScope.launch {
            if (name.isNotBlank() && subject.isNotBlank() && email.isNotBlank()) {
                contactDao.insert(ContactEntry(
                    name = name,
                    subject = subject,
                    email = email
                ))
            }
        }
    }
}