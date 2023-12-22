package com.example.studentapp.repository

import androidx.lifecycle.LiveData
import com.example.studentapp.dao.AdminDao
import com.example.studentapp.entities.Admin
import com.example.studentapp.entities.Student

class AdminRepository (private val adminDao: AdminDao){
    val allStudents: LiveData<List<Student>> = adminDao.getAllStudents();

    fun findStudents(mat: String): LiveData<List<Student>>{
         return adminDao.findByMat(mat);
    }
    suspend fun insertStudent(student: Student){
        adminDao.insertStudent(student);
    }
    suspend fun deleteStudent(student: Student){
        adminDao.deleteStudent(student);
    }
    suspend fun updateStudent(student: Student){
        adminDao.updateStudent(student.id, student.name, student.location, student.department, student.faculty, student.blackListed);
    }
    suspend fun deleteAllStudent(){
        adminDao.deleteAllStudents();
    }

    suspend fun blStudent(id: Int){
        var bl: Boolean = !adminDao.getStudent(id).blackListed
        adminDao.blStudent(id, bl)
    }
    suspend fun getStudent(id: Int) : Student{
        return adminDao.getStudent(id)
    }

    suspend fun validateAdmin(username:String, password:String) : Admin? {
        return adminDao.validateAdmin(username, password)
    }







}