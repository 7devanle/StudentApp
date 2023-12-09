package com.example.studentapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,

    @ColumnInfo(name = "mat")
    val matNo: String,

    @ColumnInfo(name = "name")
    val name: String,

    val photo:Int,

    val location: String,

    val department: String,

    val faculty: String,

    val blackListed:Boolean

)
