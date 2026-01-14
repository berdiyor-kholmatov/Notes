package com.example.listofnotes.ui.noteEdit

data class NoteEditState (
    val title: String  = "",
    val text: String = "",
    val dateOfCreating: String = "",
    val dateOfEditing: String = "",
    val isInputError: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isSaved: Boolean = false,
    val id: Int = -1,
)