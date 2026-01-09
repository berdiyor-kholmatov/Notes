package com.example.listofnotes.ui.noteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofnotes.repo.useCase.DeleteNoteUseCase
import com.example.listofnotes.repo.useCase.ObserveNotesUseCase
import com.example.listofnotes.repo.useCase.UpdateNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ListViewModel (
    private val observeNotes: ObserveNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ListViewState())
    val state = _state.asStateFlow()

    init {
        observeNotes().onEach {
                _state.value = _state.value.copy(
                    notes = it
                )
            }.launchIn(viewModelScope)

    }


    fun onEvent(event: ListEvent){
        when(event) {
            is ListEvent.DeleteButtonClicked -> {
                viewModelScope.launch {
                    deleteNoteUseCase(event.noteId)
                }
            }
            is ListEvent.IsDoneButtonClicked -> {
                viewModelScope.launch {
                    updateNoteUseCase(
                        _state.value.notes.find {
                            it.id == event.noteId
                        }!!.copy(
                            isDone = !_state.value.notes.find {
                                it.id == event.noteId
                            }!!.isDone
                        )
                    )
                }
            }
        }



    }

}