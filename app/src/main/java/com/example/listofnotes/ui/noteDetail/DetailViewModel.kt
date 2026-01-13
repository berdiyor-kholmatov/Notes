package com.example.listofnotes.ui.noteDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofnotes.repo.notesRepo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DetailViewState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailEvent) {
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