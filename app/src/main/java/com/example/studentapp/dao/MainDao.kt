package com.example.studentapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.studentapp.entities.Admin
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

    @Query("SELECT * FROM admins WHERE username = :username AND password =:password")
    suspend fun validateAdmin(username:String, password:String) : Admin?
}