package com.example.notepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY_PROPERTY_NAME
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewMoel(application: Application) : AndroidViewModel(application) {

    private val noteRepository = NoteRepository(application.applicationContext)

    private val _noteList: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()
    private val _noteLiveData: MutableLiveData<Note> = MutableLiveData<Note>()

    val mNoteList: LiveData<List<Note>> get() = _noteList
    val mNoteLiveData: LiveData<Note> get() = _noteLiveData

    fun getNotes(noteTitle: String) {
        getNoteByTitle(noteTitle)
    }

    private fun getNoteByTitle(noteTitle: String) {
        viewModelScope.launch {

        }
    }

    fun saveNoteToDB(note: Note) = noteRepository.saveNote(note)

    fun deleteNote(note: Note?) = noteRepository.deleteNote(note)

    fun getNotesFromDB() {
        viewModelScope.launch {
            val list = noteRepository.getNote()
            _noteList.value = list
        }
    }

}