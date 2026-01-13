package com.example.listofnotes.ui.noteAdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofnotes.domain.model.Note
import com.example.listofnotes.repo.notesRepo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class NoteAddViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(NoteAddState())
    val state = _state.asStateFlow()


    fun onEvent(event: NoteAddEvent) {
        when (event) {
            is NoteAddEvent.TitleChanged -> {
                _state.value = _state.value.copy(
                    title = event.title,
                    isButtonEnabled = event.title.isNotBlank() && _state.value.text.isNotBlank()
                )
            }

            is NoteAddEvent.TextChanged -> {
                _state.value = _state.value.copy(
                    text = event.text,
                    isButtonEnabled = event.text.isNotBlank() && _state.value.title.isNotBlank()
                )
            }

            NoteAddEvent.SaveButtonClicked -> {
                viewModelScope.launch {
                    repository.addNote(Note(
                        title = _state.value.title,
                        text = _state.value.text,
                        dateOfCreating = LocalDate.now().toString(),
                        dateOfEditing = "",
                        isDone = false
                    ))
                    _state.value = _state.value.copy(
                        isSaved = true
                    )
                }
            }
        }
    }


}