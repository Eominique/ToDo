package com.example.todo.di

import com.example.todo.data.TaskDao
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
abstract class TestTasksRepositoryModule{
    @Singleton
    @Binds
    abstract fun bindRepository(repo: FakeRepository): TaskDao
}