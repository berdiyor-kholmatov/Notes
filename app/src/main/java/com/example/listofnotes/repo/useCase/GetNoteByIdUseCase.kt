package com.example.listofnotes.repo.useCase

import com.example.listofnotes.domain.model.Note
import com.example.listofnotes.repo.notesRepo.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetNoteByIdUseCase (
    private val repository: NotesRepository
) {
    operator fun invoke (noteId: Int) : Flow<Note?> {
        return repository.getNoteById(noteId)
    }
}
