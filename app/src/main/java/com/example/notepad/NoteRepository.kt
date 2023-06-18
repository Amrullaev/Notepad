package com.example.notepad

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class NoteRepository(context: Context) : CoroutineScope {

    private val notepadDao = NoteDataBase.invoke(context).notepadDao()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    fun saveNote(note: Note) {
        launch {
            notepadDao.insertData(note)
        }
    }

    fun deleteNote(note: Note?) {
        launch {
            if (note != null)
                notepadDao.deleteNote(note)
        }
    }

    suspend fun getNote() = withContext(Dispatchers.IO) { notepadDao.getNotes() }
}