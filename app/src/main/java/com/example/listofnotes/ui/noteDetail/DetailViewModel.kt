package com.example.listofnotes.ui.noteDetail

import androidx.lifecycle.ViewModel
import com.example.listofnotes.repo.notesRepo.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

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
    }

}