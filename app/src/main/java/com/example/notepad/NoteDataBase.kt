package com.example.notepad

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun notepadDao(): NotepadDao

    companion object {
        @Volatile
        private var instance: NoteDataBase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context): NoteDataBase = instance ?: synchronized(LOCK) {
            instance ?: createDataBase(context).also { instance = it }
        }

        private fun createDataBase(context: Context): NoteDataBase = Room.databaseBuilder(
            context.applicationContext,
            NoteDataBase::class.java,
            "note-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}