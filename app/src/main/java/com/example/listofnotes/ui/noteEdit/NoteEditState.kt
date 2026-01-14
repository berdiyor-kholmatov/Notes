package com.example.listofnotes.ui.noteEdit

import com.example.listofnotes.domain.util.Event

data class NoteEditState (
    val title: String  = "",
    val text: String = "",
    val dateOfCreating: String = "",
    val dateOfEditing: String = "",
    val isInputError: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isSaved: Event<Boolean>,
    val id: Int = -1,
    val error : String = ""
)