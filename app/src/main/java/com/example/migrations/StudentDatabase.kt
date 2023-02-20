package com.anushka.roommigrationdemo1

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.example.migrations.StudentDAO
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Student::class],
    version = 5,
  autoMigrations = [
          AutoMigration(from =1, to = 2) ,
          AutoMigration(from =2, to = 3),
          AutoMigration(from =3, to = 4,spec = StudentDatabase.Migration3TO4::class),
          AutoMigration(from =4, to = 5,spec = StudentDatabase.Migration4To5::class),
          ]

)

abstract class StudentDatabase : RoomDatabase() {

    abstract val subscriberDAO : StudentDAO

    @RenameColumn(tableName = "student_info",fromColumnName = "student_courseName",toColumnName = "subject_name")
    class Migration3TO4 : AutoMigrationSpec

    @DeleteColumn(tableName = "student_info",columnName = "student_email")
    class Migration4To5 : AutoMigrationSpec

    companion object{
        @Volatile
        private var INSTANCE : StudentDatabase? = null
        fun getInstance(context: Context):StudentDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_data_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

