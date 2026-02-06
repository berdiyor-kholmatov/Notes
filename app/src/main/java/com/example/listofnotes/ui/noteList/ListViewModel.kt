package com.example.listofnotes.ui.noteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.listofnotes.repo.notesRepo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {


    private val _state = MutableStateFlow(ListViewState()).also {
        it.update {
            it.copy(
                notes = repository.observePaginatedNotes().cachedIn(viewModelScope)
            )
        }
    }
    val state = _state.asStateFlow()


    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.DeleteButtonClicked -> {
                viewModelScope.launch {
                    repository.deleteNote(event.noteId)
                }
            }

            is ListEvent.IsDoneButtonClicked -> {
                viewModelScope.launch {
                    val note = repository.getCurrentValueOfNoteById(event.noteId)
                    repository.updateNote(note!!.copy(isDone = !note.isDone))
                }

            }
        }


    }

}