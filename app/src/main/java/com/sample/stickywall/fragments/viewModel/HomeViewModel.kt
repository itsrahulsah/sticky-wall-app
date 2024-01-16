package com.sample.stickywall.fragments.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.stickywall.data.models.Notes
import com.sample.stickywall.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val notesRepository: NotesRepository):ViewModel() {

    val notes:LiveData<List<Notes>>
        get() = notesRepository.getAllNotes()

    fun delete(note:Notes)= viewModelScope.launch(Dispatchers.IO){ notesRepository.deleteNote(note) }

}