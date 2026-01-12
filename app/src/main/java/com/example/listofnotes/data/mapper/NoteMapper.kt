package com.example.listofnotes.data.mapper

import com.example.listofnotes.data.db.NoteEntity
import com.example.listofnotes.domain.model.Note
import com.example.listofnotes.domain.util.Mapper
import kotlin.String

class NoteMapper: Mapper<NoteEntity, Note> {
    override fun domainToModel(domain: Note): NoteEntity {
        val noteEntity = NoteEntity(
                title = domain.title,
                text = domain.text,
                dateOfCreating = domain.dateOfCreating,
                dateOfEditing = domain.dateOfEditing,
                isDone = domain.isDone,
            )
        noteEntity.id = domain.id
        return noteEntity
    }

    override fun modelToDomain(model: NoteEntity): Note {
        return Note(
            title = model.title,
            text = model.text,
            dateOfCreating = model.dateOfCreating,
            dateOfEditing = model.dateOfEditing,
            isDone = model.isDone,
            id = model.id
        )
    }

}