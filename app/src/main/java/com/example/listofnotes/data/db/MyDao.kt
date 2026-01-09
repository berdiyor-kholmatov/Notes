package com.example.listofnotes.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes")
     fun observeNotes(): Flow<List<NoteEntity>>

     @Query("SELECT * FROM notes WHERE id = :id")
     fun getUserById(id: Int): Flow<NoteEntity?>

    @Query("SELECT * FROM notes order by dateOfCreating desc")
    fun getUsersFlow() : Flow<List<NoteEntity>>

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNoteById(id: Int)

    @Query("DELETE FROM notes")
    suspend fun deleteAllUsers()
}