package com.example.studentapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studentapp.entities.Admin
import com.example.studentapp.entities.Student

@Dao
interface AdminDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdmin(admin: Admin);

    @Query("UPDATE students SET name=:name, location=:location, department=:department, faculty=:faculty, bl=:bl WHERE id=:id")
    suspend fun updateStudent(id: Int?, name: String?, location: String?, department: String?, faculty: String?, bl: Boolean?)

    @Query("UPDATE students SET bl= :bl WHERE id= :id")
    suspend fun blStudent(id: Int, bl: Boolean)

    @Delete
    suspend fun deleteStudent(student: Student);

    @Query("DELETE FROM students")
    suspend fun deleteAllStudents();

    @Query("DELETE FROM admins")
    suspend fun deleteAllAdmins();

    @Query("SELECT * from students where id = :id")
    suspend fun getStudent(id: Int): Student

    @Query("SELECT * FROM students ORDER BY id DESC")
    fun getAllStudents():LiveData<List<Student>>;

    @Query("SELECT * FROM students WHERE mat LIKE :mat")
    fun findByMat(mat: String):LiveData<List<Student>>;

    @Query("SELECT * FROM admins WHERE username = :username AND password =:password")
    suspend fun validateAdmin(username:String, password:String) : Admin?


}