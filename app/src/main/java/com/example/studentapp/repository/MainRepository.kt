package com.example.studentapp.repository

import com.example.studentapp.dao.MainDao
import com.example.studentapp.entities.Admin
import kotlinx.coroutines.flow.Flow

class MainRepository(private val mainDao: MainDao) {
     suspend fun validateAdmin(username:String, password:String) : Admin? {
        return mainDao.validateAdmin(username, password)
    }
}