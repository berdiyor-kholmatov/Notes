package com.example.listofnotes.di

import android.content.Context
import androidx.room.Room
import com.example.listofnotes.data.db.MyDao
import com.example.listofnotes.data.db.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) : MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, "notes.db")
            .fallbackToDestructiveMigration(dropAllTables = true)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideNoteDao(db: MyDatabase) : MyDao = db.getMyDao()
}