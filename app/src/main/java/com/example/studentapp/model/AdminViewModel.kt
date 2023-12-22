package com.example.studentapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.studentapp.database.AdminDatabase
import com.example.studentapp.entities.Student
import com.example.studentapp.repository.AdminRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class AdminViewModel(application: Application) : AndroidViewModel(application) {
    private val adminRepository: AdminRepository

    val allStudents: LiveData<List<Student>>

    init {
        val dao = AdminDatabase.getDatabase(application).getAdminDao();
        adminRepository = AdminRepository(dao);
        allStudents = adminRepository.allStudents;
    }

    fun insertStudent(student: Student) = viewModelScope.launch(Dispatchers.IO){
        adminRepository.insertStudent(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch(Dispatchers.IO){
        adminRepository.updateStudent(student)
    }

    fun blackListStudent(id: Int) = viewModelScope.launch(Dispatchers.IO){
        adminRepository.blStudent(id)
    }

    fun deleteStudent(student: Student) = viewModelScope.launch(Dispatchers.IO){
        adminRepository.deleteStudent(student)
    }

    fun deleteAllStudent() = viewModelScope.launch(Dispatchers.IO){
        adminRepository.deleteAllStudent()
    }

    fun searchStudent(query: String): Any = viewModelScope.launch(Dispatchers.IO){
        adminRepository.findStudents(query);
    }

    suspend fun getStudent(id: Int) = viewModelScope.launch(Dispatchers.IO){
        adminRepository.getStudent(id)
    }

}
