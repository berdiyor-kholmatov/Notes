package com.example.listofnotes.data.mapper

import com.example.listofnotes.data.db.NoteEntity
import com.example.listofnotes.domain.model.NoteTitle
import com.example.listofnotes.domain.util.Mapper

//it is not necessary, as we have as a response NoteTitle type,which is not correct, as the respond type have to be NoteTitleEntity

class NoteTitleMapper: Mapper<NoteEntity, NoteTitle> {
    override fun domainToModel(domain: NoteTitle): NoteEntity {
        return NoteEntity(
            title = domain.title,
            text = "",
            dateOfCreating = domain.dateOfCreating,
            dateOfEditing = "",
            isDone = domain.isDone,
        )
    }

    override fun modelToDomain(model: NoteEntity): NoteTitle {
        return NoteTitle(
            title = model.title,
            dateOfCreating = model.dateOfCreating,
            isDone = model.isDone,
            id = model.id,
        )
    }
}