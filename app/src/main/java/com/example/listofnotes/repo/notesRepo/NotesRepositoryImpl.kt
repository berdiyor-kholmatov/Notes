package com.example.listofnotes.repo.notesRepo

import com.example.listofnotes.data.db.MyDao
import com.example.listofnotes.data.mapper.NoteMapper
import com.example.listofnotes.domain.model.Note
import com.example.listofnotes.domain.model.NoteTitle
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl @Inject constructor(
    private val dao: MyDao,
    private val mapper: NoteMapper
): NotesRepository {
    override fun observeNotes(): Flow<List<Note>> {
        return dao.observeNotes()
            .map { entities ->
                entities.map { entity ->
                    mapper.modelToDomain(entity)
                }
            }
    }

    override fun observeNoteTitles(): Flow<List<NoteTitle>> {
        return dao.getNoteTitles()
    }

    override fun getNoteById(id: Int): Flow<Note?> {
        return dao.getUserById(id)
            .map { entity ->
                entity?.let {
                    mapper.modelToDomain(it)
                }
            }
    }

    override suspend fun addNote(note: Note) {
        dao.insert(mapper.domainToModel(note))
    }

    override suspend fun deleteNote(noteId: Int) {
        dao.deleteNoteById(noteId)
    }

    override suspend fun updateNote(note: Note) {
        val entity = mapper.domainToModel(note)
        dao.insert(entity)
        // bu notog'ri update sql query yozib update qilish kerak
    }
}