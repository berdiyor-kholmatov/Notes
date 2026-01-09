package com.example.listofnotes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false)
abstract class MyDatabase : RoomDatabase () {
    abstract fun getMyDao(): MyDao
}