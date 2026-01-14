package com.example.listofnotes.ui.noteAdd

import com.example.listofnotes.domain.util.Event


data class NoteAddState (
    val title: String  = "",
    val text: String = "",
    val isInputError: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isSaved: Event<Boolean> = Event(false),
)