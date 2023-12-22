package com.example.studentapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.studentapp.entities.Admin
import com.example.studentapp.entities.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {
    @Insert
    suspend fun insertAdmin(admin: Admin)

    @Delete
    suspend fun deleteAdmin(admin: Admin)

    @Update
    suspend fun updateAdmin(admin: Admin)

    @Query("DELETE FROM admins")
    suspend fun deleteAllAdmins()

    @Query("SELECT * FROM admins ORDER BY id DESC")
    fun getAllAdmins(): Flow<List<Admin>>

    @Insert
    suspend fun insertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Query("DELETE FROM students")
    suspend fun deleteAllStudents()

    @Query("SELECT * FROM students ORDER BY id DESC")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM admins WHERE username = :username AND password =:password")
    suspend fun validateAdmin(username:String, password:String) : Admin?


}