package com.example.listofnotes.ui.noteList

import com.example.listofnotes.domain.model.Note
import com.example.listofnotes.domain.model.NoteTitle

data class ListViewState (
    val notes: List<NoteTitle> = emptyList(),
    val selectedNote: Note? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

