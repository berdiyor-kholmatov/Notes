package com.example.listofnotes.ui.noteAdd

import com.example.listofnotes.ui.noteDetail.DetailEvent

sealed class NoteAddEvent {
    data class TitleChanged(val title: String) : NoteAddEvent()
    data class TextChanged(val text: String) : NoteAddEvent()
    object SaveButtonClicked : NoteAddEvent()
}