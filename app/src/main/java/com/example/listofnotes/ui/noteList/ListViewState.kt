package com.example.listofnotes.ui.noteList

import androidx.paging.PagingData
import com.example.listofnotes.domain.model.Note
import com.example.listofnotes.domain.model.NoteTitle

data class ListViewState (
    //val notes: PagingData<NoteTitle> = PagingData.empty(),
    val selectedNote: Note? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

