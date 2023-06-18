package com.example.notepad

import androidx.room.*

@Dao
interface NotepadDao {
    @Query("SELECT * FROM note")
    fun getNotes(): List<Note>

    @Query("DELETE FROM note")
    fun deleteData()

    @Delete
    fun deleteNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(note : Note)
}