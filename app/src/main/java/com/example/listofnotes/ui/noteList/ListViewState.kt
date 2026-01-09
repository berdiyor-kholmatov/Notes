package com.example.listofnotes.ui.noteList

import com.example.listofnotes.domain.model.Note

data class ListViewState (
    val notes: List<Note> = emptyList(),
    val selectedNote: Note? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

