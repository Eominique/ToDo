package com.example.todo.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TasksDaoTest {

    private lateinit var database: TaskDatabase

    @Before
    fun initDb() {
        // using an in-memory database because the information stored
        // here disappears when the process is killed
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            TaskDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertTaskAndGetById() = runBlockingTest {
        //Given
        val task = Task("title", false)
        database.taskDao().insert(task)

        //When
        val loaded = database.taskDao().getTaskById(task.id.toString())

        //Then
        assertThat<Task>(loaded as Task, CoreMatchers.notNullValue())
        assertThat(loaded.id, CoreMatchers.`is`(task.id))
        assertThat(loaded.title, CoreMatchers.`is`(task.title))
        assertThat(loaded.important, CoreMatchers.`is`(task.important))
        assertThat(loaded.completed, CoreMatchers.`is`(task.completed))

    }


    @Test
    fun insertTaskReplacesOnConflict() = runBlockingTest {
        //Given
        val task = Task("title", false)
        database.taskDao().insert(task)

        //When
        val newTask = Task("title2", false, false, Calendar.getInstance().time, task.id)
        database.taskDao().insert(newTask)

        //Then
        val loaded = database.taskDao().getTaskById(task.id.toString())
        assertThat(loaded?.id, CoreMatchers.`is`(task.id))
        assertThat(loaded?.title, CoreMatchers.`is`("title2"))
        assertThat(loaded?.completed, CoreMatchers.`is`(false))
        assertThat(loaded?.important, CoreMatchers.`is`(false))

    }


}