package com.example.studentapp

import android.app.Application
import com.example.studentapp.database.MainDatabase
import com.example.studentapp.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { MainDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { MainRepository(database.mainDao()) }
}