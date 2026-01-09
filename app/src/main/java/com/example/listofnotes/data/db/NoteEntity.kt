package com.example.listofnotes.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    // @ColumnInfo(name = "full_name")
    val text: String,
    val dateOfCreating: String,
    val dateOfEditing: String,
    val isDone: Boolean,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}