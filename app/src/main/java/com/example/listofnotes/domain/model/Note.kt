package com.example.listofnotes.domain.model

data class Note (
    val title: String,
    val  text: String,
    val isDone: Boolean,
    val dateOfCreating: String,
    val dateOfEditing: String,
    val id: Int = 0
)