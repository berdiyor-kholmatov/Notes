package com.example.listofnotes.repo.notesRepo

import com.example.listofnotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun observeNotes(): Flow<List<Note>>
    fun getNoteById(id: Int): Flow<Note?>
    suspend fun addNote(note: Note)
    suspend fun deleteNote(noteId: Int)
    suspend fun updateNote(note: Note)
}