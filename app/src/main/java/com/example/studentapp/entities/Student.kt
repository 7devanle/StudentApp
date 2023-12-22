package com.example.studentapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,

    @ColumnInfo(name = "mat") val matNo: String? = "SOO"+id+"2023",

    @ColumnInfo(name = "name") val name: String,

    val photo:Int,

    val location: String,

    val department: String,

    val faculty: String,

    @ColumnInfo(name="bl") val blackListed:Boolean

): Serializable
