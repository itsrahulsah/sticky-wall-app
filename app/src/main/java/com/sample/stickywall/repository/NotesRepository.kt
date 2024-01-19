package com.sample.stickywall.repository

import androidx.lifecycle.LiveData
import com.sample.stickywall.data.AppDatabase
import com.sample.stickywall.data.models.Notes

class NotesRepository(private val appDatabase: AppDatabase) {

     fun getAllNotes(): LiveData<List<Notes>> = appDatabase.notesDao().getNotes()

    suspend fun insertNote(note: Notes) = appDatabase.notesDao().insertNote(note)

    suspend fun deleteNote(note: Notes) = appDatabase.notesDao().deleteNote(note)
    suspend fun updateNote(notes: Notes) = appDatabase.notesDao().updateNote(notes)

}