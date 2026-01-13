package com.example.listofnotes.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.listofnotes.domain.model.NoteTitle
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntity: NoteEntity)



    @Query("SELECT * FROM notes")
     fun observeNotes(): Flow<List<NoteEntity>>

     @Query("SELECT * FROM notes WHERE id = :id")
     fun getUserById(id: Int): Flow<NoteEntity?>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): NoteEntity?

    @Query("SELECT * FROM notes order by dateOfCreating desc")
    fun getUsersFlow() : Flow<List<NoteEntity>>

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNoteById(id: Int)

    @Query("SELECT id, title, dateOfCreating, isDone FROM notes")
    fun getNoteTitles(): Flow<List<NoteTitle>>

    @Query("DELETE FROM notes")
    suspend fun deleteAllUsers()
}