package com.example.listofnotes.ui.noteEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofnotes.domain.model.Note
import com.example.listofnotes.repo.notesRepo.NotesRepository
import com.example.listofnotes.ui.noteAdd.NoteAddState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel(assistedFactory = NoteEditViewModel.Factory::class)
class NoteEditViewModel @AssistedInject constructor(
    private val repository: NotesRepository,
    @Assisted val noteId: Int
) : ViewModel() {
    private val _state = MutableStateFlow(NoteEditState())
    val state = _state.asStateFlow()

    init {
        getNoteById()
    }

    fun onEvent(event: NoteEditEvent) {
        when (event) {
            is NoteEditEvent.TitleChanged -> {
                _state.value = _state.value.copy(
                    title = event.title,
                    isButtonEnabled = event.title.isNotBlank() && _state.value.text.isNotBlank()
                )
            }

            is NoteEditEvent.TextChanged -> {
                _state.value = _state.value.copy(
                    text = event.text,
                    isButtonEnabled = event.text.isNotBlank() && _state.value.title.isNotBlank()
                )
            }

            NoteEditEvent.SaveButtonClicked -> {
                viewModelScope.launch {
                    repository.updateNote(
                        Note(
                            title = _state.value.title,
                            text = _state.value.text,
                            dateOfCreating = LocalDate.now().toString(),
                            dateOfEditing = "",
                            isDone = false,
                            id = _state.value.id
                        )
                    )
                    _state.value = _state.value.copy(
                        isSaved = true
                    )
                }
            }

            NoteEditEvent.BackActionWillBeApplied -> {
                val tmp = NoteAddState()
                _state.value = _state.value.copy(
                    title = tmp.title,
                    text = tmp.text,
                    isInputError = tmp.isInputError,
                    isButtonEnabled = tmp.isButtonEnabled,
                    isSaved = tmp.isSaved
                )
            }
        }
    }

    private fun getNoteById() {
        repository.getNoteById(noteId).onEach {
            if (it == null) {
                _state.value = _state.value.copy(
                    error = "Note not found"
                )
                return@onEach
            }
            _state.value = _state.value.copy(
                title = it.title,
                text = it.text,
                dateOfCreating = it.dateOfCreating,
                dateOfEditing = it.dateOfEditing,
                id = it.id
            )
        }.launchIn(viewModelScope)


    }

    @AssistedFactory
    interface Factory {
        fun create(noteId : Int): NoteEditViewModel
    }

}