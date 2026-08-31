package com.example.fragmentapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel : ViewModel() {
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _usn = MutableStateFlow("")
    val usn: StateFlow<String> = _usn

    fun setUserData(newName: String, newUsn: String) {
        _name.value = newName
        _usn.value = newUsn
    }
}
