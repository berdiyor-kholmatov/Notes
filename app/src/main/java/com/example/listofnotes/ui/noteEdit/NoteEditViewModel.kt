package com.example.listofnotes.ui.noteEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofnotes.domain.model.Note
import com.example.listofnotes.repo.notesRepo.NotesRepository
import com.example.listofnotes.ui.noteAdd.NoteAddEvent
import com.example.listofnotes.ui.noteAdd.NoteAddState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class NoteEditViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(NoteEditState())
    val state = _state.asStateFlow()


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
                    repository.updateNote(Note(
                        title = _state.value.title,
                        text = _state.value.text,
                        dateOfCreating = LocalDate.now().toString(),
                        dateOfEditing = "",
                        isDone = false,
                        id = _state.value.id
                    ))
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

    fun setNoteId(id: Int) {
        if (id == -1 || id == _state.value.id)
            return // Recomposeni oldini olish uchun
        _state.value = _state.value.copy(
            id = id
        )

        // Shu yerda db dan to'liq note olasiz

        val note = repository.getNoteById(id).onEach {
            if (it == null) {
                //something went wrong
            }
            _state.value = _state.value.copy(
                title = it?.title ?: "",
                text = it?.text ?: "",
                dateOfCreating = it?.dateOfCreating ?: "",
                dateOfEditing = it?.dateOfEditing ?: "",
            )
        }.launchIn(viewModelScope)



    }

}