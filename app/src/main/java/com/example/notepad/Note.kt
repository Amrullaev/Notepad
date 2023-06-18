package com.example.notepad

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class Note(

    @PrimaryKey(autoGenerate = true)
    val numb: Int?,
    var title: String,
    var text: String,

)
