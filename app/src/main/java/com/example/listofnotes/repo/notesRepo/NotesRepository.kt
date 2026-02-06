package com.example.listofnotes.repo.notesRepo

import androidx.paging.PagingData
import com.example.listofnotes.domain.model.Note
import com.example.listofnotes.domain.model.NoteTitle
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun observeNotes(): Flow<List<Note>>
    fun observeNoteTitles(): Flow<List<NoteTitle>>

    fun observePaginatedNotes(): Flow<PagingData<NoteTitle>>
    fun getNoteById(id: Int): Flow<Note?>
    suspend fun addNote(note: Note)
    suspend fun deleteNote(noteId: Int)
    suspend fun updateNote(note: Note)
    suspend fun getCurrentValueOfNoteById(noteId: Int): Note?
}