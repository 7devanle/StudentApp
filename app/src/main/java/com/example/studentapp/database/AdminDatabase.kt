package com.example.studentapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.studentapp.dao.AdminDao
import com.example.studentapp.entities.Admin
import com.example.studentapp.entities.Student
import com.example.studentapplication.utilities.DATABASE_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Admin::class, Student::class], version = 1, exportSchema = false)
abstract class AdminDatabase : RoomDatabase() {
    abstract fun getAdminDao() : AdminDao

//    private class AdminDatabaseCallback(private val scope: CoroutineScope) : Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    val adminDao = database.getAdminDao()
//
//                    // Delete all content here.
//                    adminDao.deleteAllAdmins()
//                    val admin1 = Admin(1, "Adeniran", "ade1234")
//                    val admin2 = Admin(2, "Bashorun", "bashorun1234")
//                    val admin3 = Admin(3, "Rehoboth", "hack123")
//                    val admin4 = Admin(4, "uu", "pp")
//
//                    adminDao.insertAdmin(admin1)
//                    adminDao.insertAdmin(admin2)
//                    adminDao.insertAdmin(admin3)
//                    adminDao.insertAdmin(admin4)
//                }
//            }
//        }
//    }

    companion object{
        @Volatile
        private var INSTANCE : AdminDatabase? = null;

        fun getDatabase(context: Context): AdminDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, AdminDatabase::class.java, DATABASE_NAME).build();

                INSTANCE = instance

                instance
            }
        }
    }

}