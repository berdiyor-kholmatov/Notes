package com.example.listofnotes.ui.noteList

sealed class ListEvent {
    data class IsDoneButtonClicked(val noteId: Int) : ListEvent()
    data class DeleteButtonClicked(val noteId: Int) : ListEvent()
}