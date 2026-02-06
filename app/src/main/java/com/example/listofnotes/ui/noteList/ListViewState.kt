package com.example.listofnotes.ui.noteList

import androidx.paging.PagingData
import com.example.listofnotes.domain.model.Note
import com.example.listofnotes.domain.model.NoteTitle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ListViewState (
    val notes: Flow<PagingData<NoteTitle>> = emptyFlow(),
    val selectedNote: Note? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

