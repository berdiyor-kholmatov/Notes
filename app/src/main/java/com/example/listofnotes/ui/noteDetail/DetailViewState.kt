package com.example.listofnotes.ui.noteDetail

data class DetailViewState(
    val id: Int = -1,
    val title: String = "",
    val text: String = "",
    val dateOfCreating: String = "",
    val dateOfEditing: String = "",
    val error: String = ""
)