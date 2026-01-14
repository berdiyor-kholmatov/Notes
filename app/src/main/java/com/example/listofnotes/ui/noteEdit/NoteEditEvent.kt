package com.example.listofnotes.ui.noteEdit

sealed class NoteEditEvent {
    data class TitleChanged(val title: String) : NoteEditEvent()
    data class TextChanged(val text: String) : NoteEditEvent()
    object SaveButtonClicked : NoteEditEvent()
    object BackActionWillBeApplied : NoteEditEvent()
}