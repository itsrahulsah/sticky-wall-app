package com.sample.stickywall.fragments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.stickywall.data.models.Notes
import com.sample.stickywall.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteViewModel(private val notesRepository: NotesRepository):ViewModel() {

    fun insertNote(notes: Notes)=viewModelScope.launch(Dispatchers.IO){
        notesRepository.deleteNote(notes)
        notesRepository.insertNote(notes)
    }
}

