package com.example.listofnotes.ui.noteAdd


data class NoteAddState (
    val title: String  = "",
    val text: String = "",
    val isInputError: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isSaved: Boolean = false,
)