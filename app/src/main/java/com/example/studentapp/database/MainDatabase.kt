package com.example.studentapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.studentapp.dao.MainDao
import com.example.studentapp.entities.Admin
import com.example.studentapp.entities.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Admin::class, Student::class], version = 1, exportSchema = false)
public abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao

    private class NoteDatabaseCallback(private val scope: CoroutineScope) :
        Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val mainDao = database.mainDao()

                    // Delete all content here.
                    mainDao.deleteAllAdmins()
                    val admin1 = Admin(1, "Adeniran", "ade1234")
                    val admin2 = Admin(2, "Bashorun Ga", "bashorun1234")
                    val admin3 = Admin(3, "Rehoboth", "hack123")
                    val admin4 = Admin(4, "uu", "pp")

                    mainDao.insertAdmin(admin1)
                    mainDao.insertAdmin(admin2)
                    mainDao.insertAdmin(admin3)
                    mainDao.insertAdmin(admin4)
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MainDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "admin_database"
                ).addCallback(NoteDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
