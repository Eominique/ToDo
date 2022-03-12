package com.example.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 3, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Wash the dishes",
                    important = true,
                    completed = true,
                    deadline = "2020:05:16"
                ))
                dao.insert(Task("Do the laundry",
                    important = true,
                    completed = true,
                    deadline = "2020:05:16"))
                dao.insert(Task("Buy groceries",
                    important = true,
                    completed = true,
                    deadline = "2020:05:16"))
                dao.insert(Task("Prepare food",
                    important = true,
                    completed = true,
                    deadline = "2020:05:16"))
                dao.insert(Task("Call mom",
                    important = true,
                    completed = true,
                    deadline = "2020:05:16"))
                dao.insert(Task("Visit grandma",
                    important = true,
                    completed = true,
                    deadline = "2020:05:16"))
                dao.insert(Task("Repair my bike",
                    important = true,
                    completed = true,
                    deadline = "2020:05:16"))

                       }
        }
    }
}