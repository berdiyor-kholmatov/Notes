package com.example.listofnotes.repo.useCase

import com.example.listofnotes.repo.notesRepo.NotesRepository

class DeleteNoteUseCase (
    private val repository: NotesRepository
) {
    suspend operator fun invoke(noteId: Int) {
        repository.deleteNote(noteId)
    }
}
