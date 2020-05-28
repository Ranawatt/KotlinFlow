package com.example.kotlinflow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinflow.data.local.dao.UserDao
import com.example.kotlinflow.data.local.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}