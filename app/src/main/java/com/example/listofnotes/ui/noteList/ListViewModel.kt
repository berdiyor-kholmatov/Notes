package com.example.listofnotes.ui.noteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofnotes.repo.notesRepo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ListViewModel @Inject constructor (
    private val repository: NotesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ListViewState())
    val state = _state.asStateFlow()

    init {
        repository.observeNoteTitles().onEach {
                _state.value = _state.value.copy(
                    notes = it
                )
            }.launchIn(viewModelScope)

    }


    fun onEvent(event: ListEvent){
        when(event) {
            is ListEvent.DeleteButtonClicked -> {
                viewModelScope.launch {
                    repository.deleteNote(event.noteId)
                }
            }
            is ListEvent.IsDoneButtonClicked -> {
                viewModelScope.launch {

                }
            }
        }



    }

}