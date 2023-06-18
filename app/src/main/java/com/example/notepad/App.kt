package com.example.notepad

import android.app.Application
import android.util.Log

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("XXXXX", "onCreate: application ")
    }
}