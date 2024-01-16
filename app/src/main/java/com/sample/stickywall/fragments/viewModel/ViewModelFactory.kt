package com.sample.stickywall.fragments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.stickywall.repository.NotesRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val notesRepository: NotesRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass){
            AddNoteViewModel::class.java -> AddNoteViewModel(notesRepository) as T
            HomeViewModel::class.java -> HomeViewModel(notesRepository) as T
            else -> {
                throw Exception("ViewModel not Found")
            }
        }
    }
}