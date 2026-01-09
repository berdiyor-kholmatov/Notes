package com.example.listofnotes.repo.useCase

import com.example.listofnotes.domain.model.Note
import com.example.listofnotes.repo.notesRepo.NotesRepository

class UpdateNoteUseCase (
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.updateNote(note)
    }
}