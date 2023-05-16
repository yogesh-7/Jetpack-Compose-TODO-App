package com.example.todo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.dao.ToDoDao
import com.example.todo.data.model.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

}