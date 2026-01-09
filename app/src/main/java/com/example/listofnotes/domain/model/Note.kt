package com.example.listofnotes.domain.model

data class Note (
    val  text: String,
    val isDone: Boolean,
    val dateOfCreating: String,
    val dateOfEditing: String,
    val id: Int = 0
)