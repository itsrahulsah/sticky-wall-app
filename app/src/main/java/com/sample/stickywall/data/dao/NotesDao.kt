package com.sample.stickywall.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sample.stickywall.data.models.Notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes ORDER BY id DESC")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)
    @Update
    suspend fun updateNote(notes: Notes)


}