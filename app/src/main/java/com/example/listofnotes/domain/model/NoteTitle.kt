package com.example.listofnotes.domain.model

data class NoteTitle(
    val id: Int,
    val title: String,
    val dateOfCreating: String,
    val isDone: Boolean,
)