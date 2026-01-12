package com.example.listofnotes.di

import com.example.listofnotes.repo.notesRepo.NotesRepository
import com.example.listofnotes.repo.notesRepo.NotesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNotesRepository(
        impl: NotesRepositoryImpl
    ) : NotesRepository = impl
}